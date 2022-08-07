<template>
  <div>

    <div style="padding: 10px 0">
      <el-input style="width: 200px" placeholder="请输入用户名" suffix-icon="el-icon-search" v-model="name"></el-input>
<!--      <el-input style="width: 200px" placeholder="请输入邮箱" suffix-icon="el-icon-message" class="ml-5" v-model="email"></el-input>-->
<!--      <el-input style="width:  200px" placeholder="请输入地址" suffix-icon="el-icon-position" class="ml-5" v-model="address"></el-input>-->
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="primary" @click="reset">重置</el-button>
    </div>



    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="确定批量删除这些数据吗？"
          @confirm="delBatch"
      >
        <el-button type="danger"  slot="reference">批量删除<i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>

<!--      <el-upload action="http://localhost:9090/role/import"-->
<!--                 :show-file-list=false-->
<!--                 accept="xlsx"-->
<!--                 :on-success="handleExcelImportAccess"-->
<!--                 style="display: inline-block">-->
<!--        <el-button type="primary" class="ml-5">导入<i class="el-icon-bottom" ></i></el-button>-->
<!--      </el-upload>-->

<!--      <el-button type="primary" class="ml-5" @click="exp" >导出<i class="el-icon-top"></i></el-button>-->
    </div>




    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column prop="id" label="id" width="80">
      </el-table-column>
      <el-table-column prop="name" label="名称">
      </el-table-column>
      <el-table-column prop="description" label="描述" >
      </el-table-column>

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>

          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除吗？"
              @confirm="deleteList(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除<i class="el-icon-remove-outline"></i></el-button>
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

    <el-dialog title="角色信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="名称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.description" autocomplete="off"></el-input>
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
export default {
  name: "Role",
  data() {
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10 ,
      name: "",
      dialogFormVisible: false,
      form: {},
      multipleSelection: [],
      headerBg: 'headerBg'
    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      //请求数据
      this.request.get("/role/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,

        }
      }).then( res=> {
        this.tableData = res.data.records
        this.total = res.data.total

      })
    },
    handleSizeChange(pageSize){
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    reset(){
      this.name=""
      this.load()
    },
    handleAdd(){
      this.dialogFormVisible=true
      this.form = {}
    },
    save(){
      this.request.post("/role",this.form).then(res => {
        if (res.data){
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        }else{
          this.$message.error("保存失败")
        }
      })
    },
    handleEdit(row){
      this.form = Object.assign({},row)
      this.dialogFormVisible = true
    },
    deleteList(id){
      this.request.delete("/role/"+id).then(res => {
        if (res.data){
          this.$message.success("删除成功")
          this.dialogFormVisible = false
          this.load()
        }else{
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.id)  //{[],[],[]} => [1,2,3]
      this.request.post("/role/del/batch",ids).then(res => {
        if (res.data){
          this.$message.success("批量删除成功")
          this.load()
        }else{
          this.$message.error("批量删除失败")
        }
      })
    },
    exp(){
      window.open("http://localhost:9090/role/export")
    },
    handleExcelImportAccess(){
      this.$message.success("文件导入成功")
      this.load()
    }
  }


}
</script>

<style >
.headerBg {
  background: #eee!important;
}
</style>