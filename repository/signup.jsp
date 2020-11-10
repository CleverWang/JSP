<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="register" class="javabeans.registerbean" scope="request" />
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=divice-width, initial-scale=1">
    <title>sign up</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/signup.css" >
  </head>
  <body>
    <div class="login">
      <form class="" action="registerservlet" method="post">
        <p>用户名 : </p><input type="text" name="name" value="" placeholder="User Name"><br>
        <p>密码 : </p><input type="password" name="password" value="" placeholder="Password"><br>
        <p>确认密码 : </p><input type="password"  name="confirm" value="" placeholder="Confirm Password"><br>
        <button type="submit" name="button">注册</button>
		<h4><a href="index.jsp">首页</a></h4>
      </form>
      
    <div><center><font color=red>
	注册反馈信息：<jsp:getProperty name="register" property="backnews"/></font>
  </body>
</html>
