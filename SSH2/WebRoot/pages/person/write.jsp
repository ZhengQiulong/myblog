
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="/SSH2/styles/index.css">
     
  </head>
  <script type="text/javascript" src="/SSH2/scripts/jquery-1.8.2.js"></script>
  <script type="text/javascript" src="ckeditor/ckeditor.js"></script>
	<script type="text/javascript">
		
		/* 提交结果，执行ajax */
		function summitNotes(){
			
			var $btn = $("input.btn");//获取按钮元素
			//给按钮绑定点击事件
			$btn.bind("click",function(){
				if($("#note").val())
				$.ajax({
					type:"post",
					url:"/SSH2/person-leaveNote.action",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
					data:{//设置数据源
						
						note:$("#note").val()//这里不要加","  不然会报错，而且根本不会提示错误地方
					},
					dataType:"json",//设置需要返回的数据类型
					success:function(data){
						$("#note").val("");
						
					},
					error:function(){
						alert("系统异常，请稍后重试！");
					}//这里不要加","
				});
			});
			
		}
		
		function isLogin()
		{
		     if(<%=session.getAttribute("user_id")%>!=null)
		      alert("你已登录！");
		      else
		      location.href="pages/login/login.jsp";
		}
		function getArticle(){
		$("#get").click(function(){
        
		$.post("person-write.action",
            {
                
                'article.title':$("#title1").val(),
                'article.type':$("#type").val(),
                'article.summary':$("#summary").val(),
                'article.keywords':$("#keywords").val(),
                'article.content':CKEDITOR.instances['editor'].getData()
            },
              function(data,status){
                alert("发布成功");
                $("#title1").val("");
                $("#type").val("");
                $("#summary").val("");
                $("#keywords").val("");
               CKEDITOR.instances['editor'].setData("");
           });
			
		});
		}
		
		/* 页面加载完成，绑定事件 */
		$(document).ready(function(){			
			summitNotes();
			CKEDITOR.replace('editor');
			getArticle();
			//点击提交，执行ajax
		});
		
	</script>
  
 <body bgcolor="#CCCCCC">

<div id="titleDiv">
<div id="menudiv">
  <ul id="menubar">
    <li><a href="person-index.action">首页</a></li>
    <li><a href="person-write.action">新随笔</a></li>
    <li><a href="person-notes.action">留言本</a></li>
    <li><a href="javascript:isLogin();">登录</a></li>
  </ul>

</div>

<div id="title">
<h1 class="STYLE1">${person.name}</h1>
</div>
</div>

<div id="leftDiv">
  <h2 class="STYLE2">公告简介</h2>
  <p class="STYLE4"><img src="${person.image}" alt="" width="142" height="154"  /></p>
  <p class="STYLE3">昵称：${person.name}</p>
  <p class="STYLE3">简介：${person.introduce}</p>
  <h2 class="STYLE2">随笔分类</h2>
    <ul class="type">
    <c:forEach items="${types}" var="one"> 
	<li><a href="person-index.action?searchId=2&keyword=${one[0]}">${one[0]}(${one[1]})</a></li>
	</c:forEach>
    </ul>
	<h2 class="STYLE2">随笔足迹</h2>
	<ul class="type">
	<c:forEach items="${dates}" var="one"> 
	<li><a href="person-index.action?searchId=1&keyword=${one[0]}">${one[0]}(${one[1]})</a></li>
    </c:forEach>
    </ul>
	<h2 class="STYLE2">阅读排行</h2>
	<ul class="type">
	<c:forEach items="${titles}" var="title">
	<li><a href="person-content.action?article_id=${title[0]}&read_num=${title[2]}">${title[1]}(${title[2]})</a></li>
	</c:forEach>
    </ul>
	<h2 class="STYLE2">留下足迹</h2>
	<form action="" class="STYLE4">
    <p><textarea name="note" id="note" cols="" rows="12"></textarea></p>
	<input type="button" value="踩脚印" class="btn" />
	</form>
</div>

<div id="article">
<h4>文章标题</h4>
<input id="title1" type="text" />
<h4>文章分类</h4>
<input id="type" type="text" />
<h4>文章摘要</h4>
<textarea rows="4" cols="120" id="summary"></textarea>
<h4>关键字</h4>
<input id="keywords" type="text" size="60" />
 <h4>编辑文章</h4>
    <textarea rows="30" cols="50" name="editor"></textarea>
    <p><input id="get" type="button" value="提交文章"/></p>
</div>
<div align="center" style="padding-top:30px;" ><p style="font-size: 10px">版权为博主所有，翻版必究</p></div>
</body>
</html>
