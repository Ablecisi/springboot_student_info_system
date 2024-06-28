<template>
  <div class="app-container">
    <!--搜索表单-->
    <el-form :inline="true" :model="searchStudent" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input
          v-model="searchStudent.name"
          placeholder="请输入学生姓名"
        />
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="searchStudent.gender" placeholder="请选择">
          <el-option label="男" value="1" />
          <el-option label="女" value="0" />
        </el-select>
      </el-form-item>

      <el-form-item label="出生时间">
        <el-date-picker
          v-model="birthday"
          clearable
          value-format="yyyy-MM-dd"
          type="date"
          placeholder="选择日期"
          style="width: 400px; margin-left: 20px"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button type="info" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>
    <!--按钮-->
    <el-row>
      <el-button type="danger" size="medium" @click="deleteByNumbers">- 批量删除</el-button>
      <el-button type="primary" size="medium" @click="dialogVisible = true; student = { image: ''};">+ 新增学生</el-button>
    </el-row>
    <!--添加数据对话框表单-->
    <el-dialog ref="form" title="编辑学生" :visible.sync="dialogVisible" width="30%">
      <el-form ref="form" :model="student" label-width="80px" size="mini">
        <el-form-item label="学号">
          <el-input id="number" v-model="student.number" readonly />
        </el-form-item>
        <el-form-item label="学生姓名">
          <el-input v-model="student.name" />
        </el-form-item>

        <el-form-item label="性别">
          <el-select v-model="student.gender" placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in genderList"
              :key="item.value"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="头像">
          <el-upload
            class="avatar-uploader"
            action="/api/upload"
            :headers="token"
            name="image"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <!--上传图像 -->
            <img v-if="student.image" :src="student.image" class="avatar" alt="图像">
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="student.phone" />
        </el-form-item>
        <el-form-item label="出生日期">
          <el-date-picker
            v-model="student.birthday"
            clearable
            type="date"
            placeholder="选择日期"
            size="small"
            style="width:100%"
          />
        </el-form-item>

        <el-form-item label="归属班级">
          <el-select v-model="student.classId" placeholder="请选择" style="width:100%">
            <el-option
              v-for="item in classList"
              :key="item.value"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="add">提交</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <br>
    <!--表格-->
    <template>
      <el-table :data="tableData" style="width: 100%" border @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column prop="number" label="学号" align="center" />
        <el-table-column prop="image" label="头像" align="center">
          <template slot-scope="{ row }">
            <el-image style="width: auto; height: 40px; border: none; cursor: pointer" :src="row.image" />
          </template>
        </el-table-column>
        <el-table-column prop="name" label="姓名" align="center" />
        <el-table-column align="center" label="性别">
          <template slot-scope="scope">
            <span style="margin-right: 10px">
              {{ scope.row.gender === 1 ? "男" : "女" }}</span>
          </template>
        </el-table-column>
        <el-table-column label="班级" align="center">
          <template slot-scope="scope">
            <span v-for="item in classList" v-show="item.id === scope.row.classId" :key="item.id">
              {{ item.name }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="电话" align="center" />
        <el-table-column align="center" label="出生日期">
          <template slot-scope="scope">
            {{ scope.row.birthday ? scope.row.birthday.replace('T', ' ') : '' }}
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作">
          <template slot-scope="scope">
            <el-button type="primary" size="small" @click="update(scope.row.number)">编辑</el-button>
            <el-button type="danger" size="small" @click="deleteByNumber(scope.row.number)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>
    <!--分页工具条-->
    <el-pagination
      :background="background"
      :current-page="currentPage"
      :page-sizes="[5, 10, 15, 200]"
      :page-size="5"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalCount"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import { page, add, update, selectByNumber, deleteByNumber } from '@/api/student.js'
import { findAll } from '@/api/class.js'
import { getToken } from '@/utils/auth'

export default {
  data() {
    return {
      background: true,
      pageSize: 5, // 每页显示的条数
      totalCount: 100, // 总记录数
      currentPage: 1, // 当前页码
      dialogVisible: false, // 添加数据对话框是否展示的标记
      searchStudent: { name: '', gender: '' },
      student: { image: '' },
      classList: [],
      number: '',
      genderList: [{ id: 1, name: '男' }, { id: 0, name: '女' }],
      birthday: '',
      classId: '',
      phone: '',
      selectedNumbers: [], // 被选中的number数组
      multipleSelection: [], // 复选框选中数据集合
      tableData: [], // 表格数据
      token: { token: getToken() } // 请求头
    }
  },

  mounted() {
    this.page() // 当页面加载完成后，发送异步请求，获取数据
    findAll().then((result) => {
      this.classList = result.data.data
      console.log(result.data.data)
    })
  },

  methods: {
    page() { // 查询分页数据
      page(
        this.searchStudent.name,
        this.searchStudent.gender,
        this.currentPage,
        this.pageSize,
        this.birthday
      ).then((res) => {
        this.totalCount = res.data.data.total
        this.tableData = res.data.data.rows
        console.log(res.data.data.rows)
      })
    },

    // 复选框选中后执行的方法
    handleSelectionChange(val) {
      this.multipleSelection = val
    },

    // 查询方法
    onSubmit() {
      this.currentPage = 1
      this.page()
    },

    clear() {
      this.searchStudent = { name: '', gender: '' }
      this.birthday = ''
      this.page()
    },
    // 添加数据
    add() {
      let operator
      if (this.student.number) {
        // 修改
        operator = update(this.student)
      } else {
        operator = add(this.student)
      }
      operator.then((resp) => {
        if (resp.data.code === 1) {
          this.dialogVisible = false
          this.page()
          this.$message({ message: '恭喜你，保存成功', type: 'success' })
          this.student = { image: '' }
        } else {
          this.$message.error(resp.data.msg)
        }
      })
    },
    update(number) {
      // 1. 打开窗口
      this.dialogVisible = true
      // 2. 发送请求
      selectByNumber(number).then((result) => {
        if (result.data.code === 1) {
          this.student = result.data.data
        }
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

    // 删除学生信息
    deleteByNumber(number) {
      this.$confirm('此操作将删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 2. 发送AJAX请求
        deleteByNumber(number).then((resp) => {
          if (resp.data.code === 1) {
            // 删除成功
            this.$message.success('恭喜你，删除成功')
            this.page()
          } else {
            this.$message.error(resp.data.msg)
          }
        })
      }).catch(() => {
        // 用户点击取消按钮
        this.$message.info('已取消删除')
      })
    },

    // 批量删除学生信息
    deleteByNumbers() {
      // 弹出确认提示框
      this.$confirm('此操作将删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 用户点击确认按钮
        // 1. 创建number数组, 从 this.multipleSelection 获取即可
        for (let i = 0; i < this.multipleSelection.length; i++) {
          this.selectedNumbers[i] = this.multipleSelection[i].number
        }

        // 2. 发送AJAX请求
        deleteByNumber(this.selectedNumbers).then((resp) => {
          if (resp.data.code === '1') {
            // 删除成功
            this.$message.success('恭喜你，删除成功')
            this.page()
          } else {
            this.$message.error(resp.data.msg)
          }
        })
      }).catch(() => {
        // 用户点击取消按钮
        this.$message.info('已取消删除')
      })
    },

    // 文件上传相关
    handleAvatarSuccess(res, file) {
      this.student.image = res.data
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  }
}
</script>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  line-height: 100px;
  text-align: center;
}

.avatar {
  width: 100px;
  height: 100px;
  display: block;
}
#number {
  cursor: not-allowed;
  border:1px solid #e2e2e2;
}

</style>
