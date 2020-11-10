<%@ page contentType="text/html;charset=gb2312"%>
<jsp:useBean id="login" class="javabeans.loginbean" scope="session" />
<html>
  <head>
    <title>log in</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/signup.css" >
  </head>
  <body>
    <div class="login">
      <form class="" action="loginservlet" method="post">
        <p>用户名：</p><input type="text" name="name" value="" placeholder="User Name"><br>
        <p>密码：</p><input type="password" name="password" value="" placeholder="Password"><br>
        <button type="submit" name="button">登录</button>
		<h4><a href="index.jsp">首页</a></h4>
      </form>   
    <div>
	<center><font color=red>登录反馈信息：<jsp:getProperty name="login" property="backnews"/><br>登录错误信息：
	<% String backnews=(String)session.getAttribute("backnews");%>
	<%=backnews%>
	</font></center>
  </body>
</html>
