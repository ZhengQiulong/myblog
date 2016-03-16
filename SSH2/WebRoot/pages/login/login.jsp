<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css">
<!--
#login_style {
	position:absolute;
	width:348px;
	height:228px;
	z-index:1;
	left: 356px;
	top: 75px;
	border-color:#CCCCCC;
	border-style:solid;
	border-width:1px;
	
}
-->
</style>
</head>
<script type="text/javascript" src="/SSH2/scripts/jquery-1.8.2.js"></script>
<script type="text/javascript" src="/SSH2/scripts/jquery.md5.js"></script>
	<script type="text/javascript">
		
		/* 提交结果，执行ajax */
		function summit(){
			
			var $btn = $("#login");//获取按钮元素
			//给按钮绑定点击事件
			$btn.bind("click",function(){
				if($("#user").val())
				$.ajax({
					type:"post",
					url:"/SSH2/login-isLogin.action",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
					data:{//设置数据源
						
						user:$.md5($("#user").val()),
						password:$.md5($("#password").val())//这里不要加","  不然会报错，而且根本不会提示错误地方
					},
					dataType:"text",//设置需要返回的数据类型
					success:function(data){
					if($.trim(data)=="-1")
						$("#tip").text("用户名或密码错误！")
						else
						  location.href="person-index.action"
					},
					error:function(){
						alert("系统异常，请稍后重试！");
					}//这里不要加","
				});
			});
			
		}
		
		/* 页面加载完成，绑定事件 */
		$(document).ready(function(){			
			summit();
			//点击提交，执行ajax
		
		});
	</script>
<body>
<div id="login_style"align="center">
<h2 style="margin-top:15px;">欢迎登陆</h2>


  <p>用户名： 
    <input type="text" name="user" id="user"/>
    </p>
  <p><br/>
    <br/>
    密&nbsp;码：
    <input type="password" name="password" id="password"/>
    </p>
  <p><br/>
    <br/>
      <input name="login" id="login" type="submit" value="登陆" />
      </p>
 <p id="tip"></p>
</div>
</body>

</html>
