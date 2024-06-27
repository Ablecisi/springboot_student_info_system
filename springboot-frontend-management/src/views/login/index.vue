<template>
  <div class="login-container">
    <div class="login-bg">
      <el-form
        ref="loginForm"
        :model="loginForm"
        :rules="loginRules"
        class="login-form"
        auto-complete="on"
        label-position="left"
      >

        <div class="title-container">
          <h3 class="title">{{ title }}</h3>
        </div>

        <el-form-item id="item" prop="username">
          <span class="svg-container">
            <svg-icon icon-class="user" />
          </span>
          <el-input
            ref="username"
            v-model="loginForm.username"
            placeholder="Username"
            name="username"
            type="text"
            tabindex="1"
            auto-complete="on"
          />
        </el-form-item>

        <el-form-item id="item" prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-input
            :key="passwordType"
            ref="password"
            v-model="loginForm.password"
            :type="passwordType"
            placeholder="Password"
            name="password"
            tabindex="2"
            auto-complete="on"
            @keyup.enter.native="handleLogin"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
          </span>
        </el-form-item>

        <el-button
          :loading="loading"
          type="primary"
          @click.native.prevent="handleLogin"
        >登陆
        </el-button>

      </el-form>
    </div>
  </div>
</template>

<script>
import { validUsername } from '@/utils/validate'
import { login } from '@/api/user'
import { setToken } from '@/utils/auth'

export default {
  name: 'Login',
  data() {
    // 用户名校验规则
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error('请输入正确的用户名'))
      } else {
        callback()
      }
    }

    // 用户名校验规则
    const validatePassword = (rule, value, callback) => {
      if (value.length < 4) {
        callback(new Error('密码长度至少为4位'))
      } else {
        callback()
      }
    }

    // 数据模型
    return {
      loginForm: {
        username: 'jinyong',
        password: '123456'
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined,
      title: '学生信息管理系统'
    }
  },

  methods: {
    // 展示密码
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    // 登录方法
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          // 调用登录后端接口
          login(this.loginForm).then((result) => {
            console.log(result)
            if (result.data.code === 1) {
              setToken(result.data.data)
              console.log('登录成功')
              this.$router.push('/')
            } else {
              this.$message.error(result.data.msg)
              this.loading = false
            }
          })
        } else {
          console.log('错误提交!!')
          return false
        }
      })
    }

  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/*reset element-ui css*/
.login-container {

  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0;
      -webkit-appearance: none;
      border-radius: 0;
      padding: 0px 5px 0px 15px; /**/
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      font-size: 22px;

      /* 注入灵魂 */
      text-shadow: 2px 2px 2px rgba(0, 0, 0, .2);
      font-family: "Berlin Sans FB";
      /* 只能控制输入的文本的颜色 */

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }

    ::placeholder{
      color: white;
      opacity: 0.8;
    }
  }

  .el-form-item {
    position: relative;
    top: 20px;
    left: 50%;
    transform: translateX(-50%);

    margin-bottom: 32px;
    padding: 0;

    width: 340px;
    height: auto;

    border-radius: 16px;

    /* 注入灵魂 */
    border-top: none;
    border-left: none;
    background-color: rgba(255, 255, 255, .1);
    /* 注入灵魂 */
    backdrop-filter: blur(3px);
    border-left: 2px solid rgba(255, 255, 255, .3);
    border-top: 2px solid rgba(255, 255, 255, .3);
    box-shadow: 2px 2px 2px rgba(0, 0, 0, .2);

    font-family: "Berlin Sans FB";
    color: white;
  }
  ::placeholder{
    color: white;
    opacity: 0.8;

  }

}

.login-container {
  .login-form {
    position: relative;
    margin: 0 auto;
    padding: 160px 35px 0;

    width: 420px;
    height: 420px;
    max-width: 100%;

    /* 注入灵魂 */
    backdrop-filter: blur(3px);
    border-left: 2px solid rgba(255, 255, 255, .3);
    border-top: 2px solid rgba(255, 255, 255, .3);
    box-shadow: 2px 2px 10px rgba(0, 0, 0, .2);

    overflow: hidden;

    /* 按钮 */
    .el-button {
      position: relative;
      top: 40px;
      left: 50%;
      transform: translateX(-50%);

      background: transparent;
      margin-top: 10px;

      width: 240px;

      cursor: auto;

      border: none;
      border-radius: 16px;

      backdrop-filter: blur(3px);
      border-left: 2px solid rgba(255, 255, 255, .3);
      border-top: 2px solid rgba(255, 255, 255, .3);
      box-shadow: 2px 2px 2px rgba(0, 0, 0, .2);

      font-size: 34px;
      font-weight: 1;
      font-family: "华文琥珀";
    }

    .el-button:focus {
      border-left:none;
      border-right: 2px solid rgba(255, 255, 255, .3);
      border-top: none;
      border-bottom: 2px solid rgba(255, 255, 255, .3);
      box-shadow: inset 2px 2px 2px rgba(0, 0, 0, .2);

      text-shadow: 1px 1px 2px rgba(0, 0, 0, .2);
    }

  }

  .tips {
    margin-bottom: 10px;

    font-size: 14px;
    color: #ff0000;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .el-form-item__error {
    left: 20px;
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    vertical-align: middle;

    width: 30px;

    display: inline-block;
    color: #889aa4;
  }

  .title-container {
    position: relative;

    .title {
      margin: 0 auto 10px auto;

      opacity: 0.9;

      text-align: center;
      text-shadow: 2px 2px 4px rgba(0, 0, 0, .2);

      font-size: 26px;
      font-weight: bold;
      font-family: '华文琥珀',cursive;

      color: white;

    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: #ffffff;
    cursor: pointer;
    user-select: none;
  }
}

</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #ffffff;
$url: url('~@/assets/background/login-bg.jpg');

.login-container {
  min-height: 100%;
  width: 100%;
  /*background: $url no-repeat center center fixed;*/
  // 背景图片，因为高斯模糊时会全页面模糊，放弃使用，
  // 在下面使用伪类元素来实现背景图片

  /*图片小一点*/
  background-size: cover;
  overflow: hidden;

  .login-form {
    background-color: rgba(255, 255, 255, .3);
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    max-width: 100%;
    margin: 0 auto;
    padding: 40px 35px 0 35px;

    border-radius: 50px;
    /* 注入灵魂 */
    backdrop-filter: blur(3px);
    border-left: 2px solid rgba(255, 255, 255, .3);
    border-top: 2px solid rgba(255, 255, 255, .3);
    box-shadow: 2px 2px 10px rgba(0, 0, 0, .2);
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #ff0000;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $light_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      user-select: none;
      font-size: 43px;
      color: $light_gray;
      text-align: center;
      font-weight: 1;
      font-family: '华文琥珀';
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $light_gray;
    cursor: pointer;
    user-select: none;
  }
}
.login-container::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: url('~@/assets/background/login-bg.jpg') no-repeat center center fixed;
  background-size: cover;
  filter: blur(5px);
  z-index: -1;
}

</style>
