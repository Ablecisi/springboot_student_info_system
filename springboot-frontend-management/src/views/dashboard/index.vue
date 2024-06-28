<template>
  <div>
    <div class="bg" />
    <div class="dashboard-container" style="margin: 0">
      <div class="first-cl" style="display: flex">
        <div class="project">
          <span>|男女占比</span>
          <div class="project_">
            <div class="planResult" style="color: #ff8c35">
              <el-progress
                type="circle"
                :percentage="project_percentage/10"
                :show-text="false"
                :stroke-color="project_colors"
              />
              <div class="textCenter">
                <div>男</div>
                <span style="color: inherit">{{ project_percentage }}</span>
              </div>
            </div>
            <div class="planResult">
              <el-progress
                type="circle"
                :percentage="project_shengyu()/10"
                :show-text="false"
                :stroke-color="project_colors"
              />
              <div class="textCenter">
                <div>女</div>
                <span>{{ (project_shengyu()) }}</span>
              </div>
            </div>
          </div>
        </div>
        <div class="electric">
          <span>|共有</span>
          <div class="electric_">
            <div class="planResult" style="color: #ff8c35">
              <el-progress
                type="circle"
                :percentage="electric_percentage/100"
                :show-text="false"
                :stroke-color="electric_colors"
              /><!--:format="text"-->
              <div class="textCenter">
                <div>人数</div>
                <span style="color: inherit">{{ electric_percentage }}</span>
              </div>
            </div>
          </div>
        </div>
        <div class="project_msg">
          <div class="project_msg_">
            <el-table
              :data="tableData"
              style="width: 100%"
              max-height="250"
            >
              <el-table-column prop="projectId" label="序号" width="80" />
              <el-table-column prop="projectName" label="名称" width="180" />
              <el-table-column prop="planStatus" label="状态" width="100" />
              <el-table-column prop="inStatus" label="现在状态" width="100" />
            </el-table>
            <el-pagination
              :current-page="currentPage"
              :background="background"
              :page-sizes="[100, 200, 300, 400]"
              :page-size="300"
              layout="total, sizes, prev, pager, next, jumper"
              :total="totalCount"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
            />
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import { page, ZhanBi } from '@/api/planlist'

export default {

  name: 'Dashboard',
  data() {
    return {
      project_total: 100,
      project_percentage: 70,
      electric_total: 100,
      electric_percentage: 30,
      background: true,
      // 每页显示的条数
      pageSize: 5,
      // 总记录数
      totalCount: 100,
      // 当前页码
      currentPage: 1,
      /* 不同百分比有不同颜色*/
      project_colors: [
        { project_percentage: 20, color: '#83f56c' },
        { project_percentage: 30, color: '#74f176' },
        { project_percentage: 70, color: '#5cb87a' },
        { project_percentage: 80, color: '#1989fa' },
        { project_percentage: 90, color: '#e6a23c' }
      ],
      electric_colors: [
        { electric_percentage: 20, color: '#f56c6c' },
        { electric_percentage: 40, color: '#e6a23c' },
        { electric_percentage: 70, color: '#5cb87a' },
        { electric_percentage: 80, color: '#1989fa' },
        { electric_percentage: 90, color: '#36ff0e' }
      ],

      tableData: []
    }
  },
  mounted() {
    this.page()
    this.ZhanBi()
  },
  methods: {
    /* 计算剩余的*/
    project_shengyu() {
      return (this.project_total) - this.project_percentage
    },
    page() {
      page(
        this.currentPage,
        this.pageSize
      ).then((res) => {
        console.log(res)
        this.totalCount = res.data.data.total
        this.tableData = res.data.data.rows
      })
    },
    // 分页
    handleSizeChange(val) {
      // 重新设置每页显示的条数
      this.pageSize = val
      this.page()
    },

    handleCurrentChange(val) {
      // 重新设置当前页码
      this.currentPage = val
      this.page()
    },

    ZhanBi() {
      ZhanBi().then((res) => {
        this.project_total = (res.data.data.total)
        this.project_percentage = (res.data.data.man)
        this.electric_percentage = (res.data.data.total)
        console.log(res)
      })
    }

  }
}
</script>

<style lang="scss" scoped>

.dashboard-container {
  position: absolute;
  width: 100%;
  height: 100%;
  background: url("../../assets/background/wallpaper.png") no-repeat center center/cover;

  .project{
    margin: 30px 30px 30px 30px;
    width: 400px;
    height: 190px;
    border-radius: 50px ;
    box-shadow: 2px 1px 20px #d8d8d8;
    background-color: #ffffff;
    /*border: solid #000000 2px;*/
    .project_ {
      margin: 0;
      padding: 0;
      width: auto;
      height: auto;
      display: flex;
      justify-content: space-around;
    }
    span {
      position: relative;
      top: 10px;
      font-size: 18px;
      color: #999999;
      font-weight: 600;
    }
    .planResult {
      position: relative;
      text-align: center;
      width: 200px;
      margin: 20px 0;
      .textCenter {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        div {
          font-size: 15px;
          color: #999999;
          font-weight: 600;
          margin-bottom: 5px;
        }
        span {
          font-size: 25px;
          color: #d76d15;
        }
      }
    }
  }

  .electric {
    background-color: #ffffff;
    margin: 30px 30px 30px 30px;
    width: 200px;
    height: 190px;
    border-radius: 50px ;
    box-shadow: 2px 1px 20px #d8d8d8;
    /*border: solid #000000 2px;*/
    .electric_ {
      margin: 0;
      padding: 0;
      width: auto;
      height: auto;
      display: flex;
      justify-content: space-around;
    }
    span {
      font-size: 18px;
      color: #999999;
      font-weight: 600;
    }
    .planResult {
      position: relative;
      text-align: center;
      width: 200px;
      margin: 20px 0;
      .textCenter {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        div {
          font-size: 15px;
          color: #999999;
          font-weight: 600;
          margin-bottom: 5px;
        }
        span {
          font-size: 25px;
          color: #6cd715;
        }
      }
    }
  }
  .project_msg {
    background-color: #ffffff;
    margin: 30px 30px 30px 30px;
    width: 600px;
    height: 350px;
    border-radius: 50px ;
    box-shadow: 2px 1px 20px #d8d8d8;
    /*border: solid #000000 2px;*/
    .project_msg_ {
      margin: 15px;
      padding: 0;
      width: auto;
      height: auto;
    }
  }
  .fix_tj {
    margin: 30px 0 0 0;
    width: auto;
    height: 190px;
    .fix {
      margin: 0 0 0 0;
      .yuan {
        /*画一个实心圆*/
        position: relative;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 80px;
        height: 80px;
        border-radius: 50%;
        background-color: #d76d15;
        margin: 0 auto;
      }
    }
    span {
      font-size: 18px;
      color: #999999;
      font-weight: 600;
    }

  }
  .second_cl {
    position: absolute;
    left: 100px;
    top: 300px;
    background-color: #ffffff;
    margin: 30px 30px 30px 30px;
    width: 500px;
    height: 400px;
    border-radius: 50px ;
    box-shadow: 2px 1px 20px #d8d8d8;
    .dianlan {
      width: auto;
      height: auto;
      margin: 45px 30px 50px 30px;
      img {
        margin: 15px 10px 10px 10px;
        width: 200%;
        height: 100%;
      }
    }
  }
}

.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}

.transition-box {
  margin-bottom: 10px;
  width: 200px;
  height: 100px;
  border-radius: 4px;
  background-color: #409EFF;
  text-align: center;
  color: #fff;
  padding: 40px 20px;
  box-sizing: border-box;
  margin-right: 20px;
}
</style>
