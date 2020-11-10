<%@ page contentType="text/html;charset=gb2312"%>
<jsp:useBean id="dump" class="javabeans.dumpbean" scope="request" />
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/dump.css">
  </head>
<body>
     <div class="text-center">
      <header>
        <div class="sign" style="text-align: left">
		  <h1><center><font color="white">设备报废处理系统</font></center><h1>
        </div>
      </header>
	 <div>
	 <br>
	 <%String operationMan=(String)session.getAttribute("operationMan");
	 if(operationMan==null){%>
	  <h3><font color=red>请先登录！</font></h3>
	  <%}
	 else if(operationMan.equals("admin")) {%>
      <form class="" action="dumpservlet" method="post">
		<p>内部编号:</p><input type="text" name="internalNum" value="" placeholder="Internal Number"><br>
		<p>报废原因:</p><input type="text" name="dumpReason" value="" placeholder="Dump Reason"><br>
	    <p>批准人员:</p><input type="text" name="agreeMan" value="" placeholder="Approver"><br>
		<p>经手人员:</p><input type="text" name="dumpHandler" value="" placeholder="Operator"><br>
		<p>处理方式:</p><input type="text" name="handleWay" value="" placeholder="Hadle Way"><br>		
        <button type="submit" name="button">提交</button>
		<h4><a href="index.jsp">首页</a></h4>
      </form>
	  <center><font color=red>
	报废反馈信息：<jsp:getProperty name="dump" property="backnews"/></font>
      <%}
	  else{%>
      	<h3><font color=red>您没有使用该功能的权限！</font></h3>
	  <%}%>	  
	  
     <div>
	</body>
</html>