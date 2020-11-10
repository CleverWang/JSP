<%@ page contentType="text/html;charset=gb2312"%>
<jsp:useBean id="lend" class="javabeans.lendbean" scope="request" />
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/lend.css">
  </head>
<body>
     <header>
       <div class="sign" style="text-align: left">
		 <h1><center><font color="white">设备转借申请系统</font></center><h1>
       </div>
     </header>
	 <div>
	 <br>
	 <%
	 String operationMan=(String)session.getAttribute("operationMan");
	 if(operationMan==null){%>
	  <center><h3><font color=red>请先登录！</font></h3></center>
	  <%}
	  else {%>
      <form class="" action="lendservlet" method="post">
	    <p>内部编号:</p><input type="text" name="internalNum" value="" placeholder="Internal Number"><br>		
        <p>借用数量:</p><input type="text" name="num" value="" placeholder="Lend Number"><br>
        <p>借用部门:</p><input type="text" name="lendDepart" value="" placeholder="Lend Department"><br>
		<p>经手人员:</p><input type="text" name="handler" value="" placeholder="Handler"><br>
        <p>用途:</p><input type="text" name="use" value="" placeholder="Usage"><br>
		<p>拟还日期:</p><input type="text" name="intendedDate" value="" placeholder="Intended Date"><br>
        <button type="submit" name="button">提交</button>
		<h4><a href="index.jsp">首页</a></h4>
      </form>   
     <div>
	 <div><center><font color=red>
	转借反馈信息：<jsp:getProperty name="lend" property="backnews"/></font>
	  <%}%>
	</body>
</html>
