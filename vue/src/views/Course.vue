<template>
  <div>

    <div style="padding: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
<!--      <el-input style="width: 200px" placeholder="请输入邮箱" suffix-icon="el-icon-message" class="ml-5" v-model="email"></el-input>-->
<!--      <el-input style="width:  200px" placeholder="请输入地址" suffix-icon="el-icon-position" class="ml-5" v-model="address"></el-input>-->
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="primary" @click="reset">重置</el-button>
    </div>



    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd" v-if="user.role==='ROLE_ADMIN'">新增<i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="确定批量删除这些数据吗？"
          @confirm="delBatch">
        <el-button type="danger" slot="reference" v-if="user.role==='ROLE_ADMIN'">批量删除<i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>

    <el-table :data="tableData" border stripe
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column prop="id" label="id" width="80">
      </el-table-column>
      <el-table-column prop="name" label="课程名称" width="140">
      </el-table-column>
      <el-table-column prop="score" label="课程学分">
      </el-table-column>
      <el-table-column prop="times" label="课时" width="120">
      </el-table-column>
      <el-table-column prop="teacher" label="授课老师" width="120">
      </el-table-column>
      <el-table-column label="开课状态">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.state"
                     active-color="#13ce66"
                     inactive-color="#ccc"
                     @change="changeEnable(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="280" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="selectCourse(scope.row.id)">选课</el-button>
          <el-button type="success" @click="handleEdit(scope.row)" v-if="user.role==='ROLE_ADMIN'">编辑<i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除吗？"
              @confirm="deleteList(scope.row.id)">
            <el-button type="danger" slot="reference" v-if="user.role==='ROLE_ADMIN'">删除<i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>


    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="课程名">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="课程学分">
          <el-input v-model="form.score" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="课时">
          <el-input v-model="form.times" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="开课老师">
          <el-select clearable v-model="form.teacherId" placeholder="请选择角色" style="width: 100%">
            <el-option v-for="item in teachers" :key="item.id" :label="item.nickname" :value="item.id"></el-option>
          </el-select>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>



<script>
import {serverIp} from "../../public/config";

export default {
  name: "Course",
  data() {
    return {
      serverIp: serverIp,
      tableData: [],
      name: '',
      form: {},
      multipleSelection: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      dialogFormVisible: false,
      teachers: [],
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}

    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      //请求数据
      this.request.get("/course/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total

      })
      //请求数据
      this.request.get("/user/role/ROLE_TEACHER").then(res =>{
        this.teachers = res.data

      })
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    reset() {
      this.name = ""
      this.load()
    },

    deleteList(id) {
      this.request.delete("/course/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
      this.load()
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)  //{[],[],[]} => [1,2,3]
      this.request.post("/course/del/batch", ids).then(res => {
        if (res.code === '200') {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    handleCourseUploadAccess(res) {
      console.log(res)
      this.load()
    },
    changeEnable(row) {
      this.request.post("/course/update", row).then(res => {
        if (res.code === '200') {
          this.$message.success("操作成功")
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    save() {
      this.request.post("/course", this.form).then(res => {
        if (res.data) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    handleAdd(){
      this.dialogFormVisible=true
      this.form = {}
    },
    handleEdit(row){
      this.form = Object.assign({},row)
      this.dialogFormVisible = true
    },
    selectCourse(courseId){
    this.request.post('/course/studentCourse/'+courseId +"/"+this.user.id).then(res =>{
      if (res.code ==='200') {
        this.$message.success("选课成功")
      } else {
        this.$message.error(res.msg)
      }
    })
    },


  }
}
</script>

<style scoped>

</style>