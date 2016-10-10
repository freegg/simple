<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=request.getContextPath()%>/" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="styles/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="styles/bootstrap-table.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="scripts/sweetalert.min.js"></script>
<link href="styles/sweetalert.css"   rel="stylesheet" type="text/css">
<script type="text/javascript" src="scripts/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="scripts/bootstrap-table.min.js"></script>
</head>
<body>
<div class="container">
<form role="form" class="col-md-5">
  <div class="form-group">
    <label for="exampleInputEmail1">User</label>
    <input type="text" class="form-control" id="username" placeholder="Enter user">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" id="password" placeholder="Password">
  </div>
  <div class="form-group">
    <label for="exampleInputFile">File input</label>
    <input type="file" id="exampleInputFile">
    <p class="help-block">Example block-level help text here.</p>
  </div>
  <div class="checkbox">
    <label>
      <input type="checkbox" id="che"> password
    </label>
  </div>
  <button type="button" class="btn btn-default" id="sub">Submit</button>
</form>
</div>
</body>
<script type="text/javascript">
console.log('${userJSON}');
if('${userJSON}'!=""&&'${userJSON}'!=""){
	var sj = $.parseJSON('${userJSON}');
	$("#username").val(sj.username);
	$("#password").val(sj.password);
}
var cook ="";
$("#sub").click(function(){
	var zhen = $("#che").is(":checked");
	if(zhen){
		cook=1;
	}else{
		cook="";
	}
	$.ajax({
		url:"user/login",
		data:{
		  user:$("#username").val(),
		  password:$("#password").val(),	
		  cooki:cook
		},
		type:"POST",
		success:function(data){
			if(data!=1){
				swal("登陆成功");
				var sh = data.replace(/\"/g, "");
				//window.location.href = "user/main;jsessionid="+sh;
				window.location.href = "user/sock";
			}else{
				swal("用户名或密码错误，请重新输入");
			}
		}
	})
})
</script>
</html>