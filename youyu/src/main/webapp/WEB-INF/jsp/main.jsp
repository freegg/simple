<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<script type="text/javascript" src="scripts/sockjs.min.js"></script>
<%
String username = (String)session.getAttribute("username");
%>
</head>
<body>
<div id="cc"></div>
<a type="button" href = "user/logout"class="btn btn-default" id="logout" >logout</a>
<div id="message"></div>
<a  href="user/tt" class="btn btn-class">zhong</a>
</body>
<script>
var name = "<%= username %>";
$("#cc").html("welcome,  "+name);
var websocket = null;

//判断当前浏览器是否支持WebSocket
if('WebSocket' in window){
    websocket = new WebSocket("ws://localhost:8083/you/myHandler");
}
else{
    alert('Not support websocket')
}
 
//连接发生错误的回调方法
websocket.onerror = function(){
    setMessageInnerHTML("error");
};
 
//连接成功建立的回调方法
websocket.onopen = function(event){
    setMessageInnerHTML("open");
}
 
//接收到消息的回调方法
websocket.onmessage = function(event){
	console.log("收到消息");
	console.log(event);
    setMessageInnerHTML(event.data);
}
 
//连接关闭的回调方法
websocket.onclose = function(){
    setMessageInnerHTML("close");
}
 
//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function(){
    websocket.close();
}
 
//将消息显示在网页上
function setMessageInnerHTML(innerHTML){
    document.getElementById('message').innerHTML += innerHTML + '<br/>';
}
 
//关闭连接
function closeWebSocket(){
    websocket.close();
}
 
//发送消息
function send(){
    var message = document.getElementById('text').value;
    websocket.send(message);
}
</script>
</html>