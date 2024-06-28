<template>
  <!-- 导航栏组件 -->
  <div class="navbar">
    <!-- 菜单组件，根据侧边栏的打开状态设置活动状态，点击时切换侧边栏 -->
    <hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />

    <!-- 导航组件 -->
    <breadcrumb class="breadcrumb-container" />

    <!-- 右侧菜单 -->
    <div class="right-menu">
      <!-- 下拉菜单，点击头像触发 -->
      <el-dropdown class="avatar-container" trigger="click">
        <!-- 头像容器 -->
        <div class="avatar-wrapper">
          <!-- 用户头像 -->
          <!--使用src动态获取src-->
          <img :src="src" class="user-avatar" alt="头像">
          <!--https://ablecisi-springboot-web-framework00.oss-cn-chengdu.aliyuncs.com/5dd0844e-6c6e-4b3c-a422-82828c748551.jpg-->
          <!-- 下拉箭头图标 -->
          <i class="el-icon-caret-bottom" />
        </div>
        <!-- 下拉菜单内容 -->
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <!-- 首页链接 -->
          <router-link to="/">
            <el-dropdown-item>
              首页
            </el-dropdown-item>
          </router-link>

          <!-- 退出按钮，点击时执行退出操作 -->
          <el-dropdown-item divided @click.native="logout">
            <span style="display:block;">退出</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex' // 引入vuex的mapGetters
import Breadcrumb from '@/components/Breadcrumb' // 引入Breadcrumb组件
import Hamburger from '@/components/Hamburger' // 引入Hamburger组件
import {getToken, removeToken} from '@/utils/auth' // 引入auth工具的removeToken方法
import {jwtDecode} from "jwt-decode"; // 引入jwt-decode库

export default {
  name: 'Navbar', // 组件名
  components: {
    Breadcrumb, // 导入Breadcrumb组件
    Hamburger // 导入Hamburger组件
  },
  computed: { // 动态获取后端的头像
    src() {
      // 默认头像
      return this.parseToken()===null ? 'https://ablecisi-springboot-web-framework00.oss-cn-chengdu.aliyuncs.com/1541d16a-72d7-4a1e-9665-f3461fd58ffd.jpg' : this.parseToken()
    },
    // 使用mapGetters映射vuex中的sidebar和avatar状态
    ...mapGetters([
      'sidebar',
      'avatar'
    ])
  },
  mounted() {
    this.parseToken() // 获取并解析token
  },
  methods: {
    // 切换侧边栏的方法
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    // 退出的方法
    async logout() {
      removeToken() // 移除token
      this.$router.push('/login') // 跳转到登录页面
    },
    // 获取并解析token
    parseToken() {
      const token = getToken() // 获取token
      // 解析JWt token
      let decoded = jwtDecode(token)
      console.log(decoded) // 打印解析后的token
      return decoded.avatar // 返回解析后的头像
    }
  }
}
</script>
<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 42px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
