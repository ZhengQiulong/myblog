<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Simpleton Demo | struts+ajax返回json类型数据</title>

<link rel="shortcut icon" type="image/x-icon" href="/SSH2/images/Icon.png" />
<link rel="stylesheet" type="text/css" href="/SSH2/styles/base.css" />

</head>
<body background="images/bg.gif">
	
	<div id="div_json">
		<h5>录入数据</h5>
		<br />
		<form action="#" method="post">
			<label for="name">姓名：</label><input type="text" name="name" />
			<label for="age">年龄：</label><input type="text" name="age" />
			<label for="position">职务：</label><input type="text" name="position" />
			<input type="button" class="btn" value="提交结果"/>
		</form>
		<br />
		<h5>显示结果</h5>
		<br />
		<ul>
			<li>姓名：<span id="s_name">赞无数据</span></li>
			<li class="li_layout">年龄：<span id="s_age">暂无数据</span></li>
			<li class="li_layout">职务：<span id="s_position">暂无数据</span></li>
		</ul>
	</div>
	
	<div id="authorgraph"><img alt="" src="/SSH2/images/autograph.gif"></div>
	
	<script type="text/javascript" src="/SSH2/scripts/jquery-1.8.2.js"></script>
	<script type="text/javascript">
		
		/* 提交结果，执行ajax */
		function btn(){
			
			var $btn = $("input.btn");//获取按钮元素
			//给按钮绑定点击事件
			$btn.bind("click",function(){
				
				$.ajax({
					type:"post",
					url:"/SSH2/json-excuteAjax.action",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
					data:{//设置数据源
						name:$("input[name=name]").val(),
						age:$("input[name=age]").val(),
						position:$("input[name=position]").val()//这里不要加","  不然会报错，而且根本不会提示错误地方
					},
					dataType:"json",//设置需要返回的数据类型
					success:function(data){
						$("#s_name").text(""+data.name+"");
						$("#s_age").text(""+data.age+"");
						$("#s_position").text(""+data.position+"");
						
					},
					error:function(){
						alert("系统异常，请稍后重试！");
					}//这里不要加","
				});
			});
		}
	
		/* 页面加载完成，绑定事件 */
		$(document).ready(function(){			
			btn();//点击提交，执行ajax
		});
	</script>
</body>
</html>