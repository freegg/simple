<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试</title>
<link href="styles/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="styles/bootstrap-table.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="scripts/sweetalert.min.js"></script>
<link href="styles/sweetalert.css"   rel="stylesheet" type="text/css">
<script type="text/javascript" src="scripts/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="scripts/bootstrap-table.min.js"></script>
</head>
<body>
<div>
<button type="button" class="btn btn-default" id="ins">insert</button>
<button type="button" class="btn btn-default" id="somedel">批量删除</button>
<div class="col-md-12" style="padding-left:10px;">
		<table id="tblApproval" class="table table-hover" data-locale="zh-CN">
		    <thead>
		        <tr>
		            <th class="check">check</th>
		        	<th class="exceptionid">id</th>
		            <th class="transmitcd">name</th>
		            <th class="applyid">age</th>
		            <th class="admitflg">password</th>
		            <th class="admitflg">cc</th>
		        </tr>
		    </thead>
		</table>
	</div>                                                
</div>
<!-- 批量删除的modal -->        
  <div class="modal fade" id="mod">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" id="clo"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
      	<form role="form" >
      			<div class="form-group">
			    <label for="exampleInputEmail1">id</label>
			    <input type="number" class="form-control" id="id" placeholder="id">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputPassword1">name</label>
			    <input type="text" class="form-control" id="name" placeholder="name">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputPassword1">Password</label>
			    <input type="password" class="form-control" id="password" placeholder="Password">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputPassword1">age</label>
			    <input type="number" class="form-control" id="age" placeholder="age">
			  </div>
			 <button type="button" class="btn btn-default" id="com">commit</button>
      	</form>
      </div>
    </div>
  </div>
</div>   
<!--单个更新modal-->         
 <div class="modal fade" id="upda">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" id="clos"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
      	<form>
		  <div class="form-group">
		    <label for="exampleInputPassword1">name</label>
		    <input type="text" class="form-control" id="updaname" placeholder="name">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Password</label>
		    <input type="text" class="form-control" id="updapassword" placeholder="Password">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">age</label>
		    <input type="number" class="form-control" id="updaage" placeholder="age">
		  </div>
		 <button type="button" class="btn btn-default" id="updacom">commit</button>
      	</form>
      </div>
    </div>
  </div>
</div>  
</body>
<script type="text/javascript">
var params=[];
$("#ins").click(function(){
	$("#mod").modal();
})
//批量删除
$("#somedel").click(function(){
	params=[];
	 var selectedobj = $('#tblApproval').bootstrapTable('getSelections');
	 $.each(selectedobj,function(key,value){
		var param={};
		param.id=value.id;
		params.push(param);
	 })
	 $.ajax({
		 url:"user/somedel",
		 data:JSON.stringify(params),
		 type:"POST",
		 dataType : 'json',
		 contentType:"application/json",
		 success:function(data){
			if(data==params.length){
				swal("删除成功");
				$("#tblApproval").bootstrapTable("refresh");
			}else{
				swal("删除失败");
			}		 
		 }
	 })
})
//单个插入
$("#com").click(function(){
		$.ajax({
			url:"user/inse",
			data:{id:$("#id").val(),
				  name:$("#name").val(),
				  age:$("#age").val(),
				  password:$("#password").val()},
			type:"GET",
			success:function(yu){
				if(yu==1){
					$("#clo").trigger("click");			
				}else{
				}
			}
		})
	})
$("").ready(function(){
	//分页加载
	$("#tblApproval").bootstrapTable({
		 url:"user/twosel",
		 queryParamsType : "limit",   
			  contentType: "application/x-www-form-urlencoded",
			  queryParams: function queryParams(params) { 
				  return {
			            pageSize: params.pageSize,
			            offset: params.offset,
			            pageNumber:params.pageNumber,
			            limit:params.limit
			        }            
	            },  
	           // sidePagination:'server',//设置为服务器端分页
	            pagination: true,     //是否显示分页（*）
	            pageNumber:1,  
	    	    pageSize: 3,      //每页的记录行数（*）
	    	   // clickToSelect: true,
	    	    columns:[{
	    	    	 checkbox: true,
	    	    },{
	    	    	  field: 'id',
	    	    },{
	    	    	  field: 'name',
	    	    },{
	    	    	  field: 'age',
	    	    },{
	    	    	  field: 'password',
	    	    },{
	    	    	  field: 'money2',
	    	    	  formatter:function(value, row, index){
	    	    		  console.log(value);
	    	    		  return value.money;
						}
	    	    }
	    	    ,{
	    	    	formatter:function(value, row, index){
	    	    		var del ="<button type='button'  class='btn btn-success del' onclick='del("+row.id+")'>delete</button>"; 
						del+="<button type='button'  class='btn btn-warning' onclick='update("+row.id+','+row.age+','+row.name+','+row.password+")'>update</button>";
	    	    		return del;
					}
	    	    }]
				})
})
function update(upid,upage,upname,uppassword){
	$("#upda").modal();
	$("#updaage").val(upage);
	$("#updaname").val(upname);
	$("#updapassword").val(uppassword);
	$("#updacom").click(function(){
		$.ajax({
			url:"user/update",
			data:{id:upid,age:$("#updaage").val(),name:$("#updaname").val(),password:$("#updapassword").val()},
			type:"GET",
			success:function(data){
				if(data==1){
					swal("更新成功");
					$("#clos").trigger("click");		
					$("#tblApproval").bootstrapTable("refresh");
				}else{
					swal("更新失败");
				}
			}
		})
	})
}
//单个删除的方法
function del(id){
	swal({title: "您确定要删除吗",
		showCancelButton: true,   
		confirmButtonColor: "#DD6B55",   
		confirmButtonText: "删除",
		cancelButtonText: "取消",
		closeOnConfirm: false }, 
		function(){
			$.ajax({
				url:"user/del",
				data:{userId:id},
				type:"GET",
				success:function(data){
					if(data==1){
						//swal.close();
						swal("删除成功");
						$("#tblApproval").bootstrapTable("refresh");
					}else{
						swal("删除失败");
					}
				}
			})
			});
}
</script>
</html>