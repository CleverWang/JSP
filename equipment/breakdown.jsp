<%@ page contentType="text/html;charset=gb2312"%>
<jsp:useBean id="breakdown" class="javabeans.breakdownbean" scope="request" />
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/breakdown.css">
  </head>
<body>
     <div class="text-center">
      <header>
        <div class="sign" style="text-align: center">
		  <h1><font color="white">设备故障处理系统</font><h1>
        </div>
      </header>
	 <div>
	 <br>
	  <%String operationMan=(String)session.getAttribute("operationMan");
	  if(operationMan==null){%>
	  <h3><font color=red>请先登录！</font></h3>
	  <%}
	 else if(operationMan.equals("student")) {%>
      <form class="" action="breakdownservlet" method="post">
		<p>内部编号:</p><input type="text" name="internalNum" value="" placeholder="Internal Number"><br>
		<p>故障原因:</p><input type="text" name="breakdownReason" value="" placeholder="Breakdown Reason"><br>
        <button type="submit" name="button">提交</button>
		<h4><a href="index.jsp">首页</a></h4>
      </form> 
	  <center><font color=red>设备故障处理反馈信息：<jsp:getProperty name="breakdown" property="backnews"/></font></center>
	  <%}
	  else if(operationMan.equals("admin")){%>
      <form class="" action="breakdownservlet" method="post">
		<p>内部编号:</p><input type="text" name="internalNum" value="" placeholder="Internal Number"><br>
		<p>故障原因:</p><input type="text" name="breakdownReason" value="" placeholder="Breakdown Reason"><br>
		<p>维修时间:</p><input type="text" name="fixTime" value="" placeholder="Fix Time"><br>
		<p>维修人员:</p><input type="text" name="fixMan" value="" placeholder="Fix Man"><br>
		<p>维修结果:</p><input type="text" name="fixResult" value="" placeholder="Fix Result"><br>
		<p>维修花费:</p><input type="text" name="fixBill" value="" placeholder="Fix Bill"><br>
        <button type="submit" name="button">提交</button>
		<h4><a href="index.jsp">首页</a></h4>
      </form> 
	  <center><font color=red>设备故障处理反馈信息：<jsp:getProperty name="breakdown" property="backnews"/></font></center>
      <%}%> 
     <div>
	</body>
</html>