<%@ page contentType="text/html;charset=gb2312"%>
<jsp:useBean id="buy" class="javabeans.buybean" scope="request" />
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/buy.css">
  </head>
<body>
     <div class="text-center">
      <header>
        <div class="sign" style="text-align: left">
		  <h1><center><font color="white">商品录入系统</font></center><h1>
        </div>
      </header>
	 <div>
	 <br>
	 <%
	 String operationMan=(String)session.getAttribute("operationMan");
	 if(operationMan==null){%>
	  <h3><font color=red>请先登录！</font></h3>
	  <%}
	 else if(operationMan.equals("admin")) {%>
      <form class="" action="buyservlet" method="post">
        <p>商品名称:</p><input type="text" name="name" value="" placeholder="Name"><br>
        <p>购入时间:</p><input type="text" name="buyTime" value="" placeholder="Bought Time"><br>
		<p>商品品牌:</p><input type="text" name="brand" value="" placeholder="Brand"><br>
		<p>商品品牌:</p><input type="text" name="model" value="" placeholder="Model"><br>
		<p>商品类型:</p><input type="text" name="type" value="" placeholder="Type"><br>
		<p>购入数量:</p><input type="text" name="num" value="" placeholder="Amount"><br>
		<p>商品编号:</p><input type="text" name="internalNum" value="" placeholder="Internal Number"><br>
		<p>生产厂商:</p><input type="text" name="department" value="" placeholder="Manufacturer"><br>
	    <p>购买人:</p><input type="text" name="reciever" value="" placeholder="Buyer"><br>
		<p>存放位置:</p><input type="text" name="position" value="" placeholder="Position"><br>		
        <button type="submit" name="button">提交</button>
		<h4><a href="index.jsp">首页</a></h4>
      </form>
	  <center><font color=red>商品录入系统反馈信息：<jsp:getProperty name="buy" property="backnews"/></font></center>
      <%}
	  else{%>
      	<h3><font color=red>您没有使用该功能的权限！</font></h3>
	  <%}%>
     
     <div>
	 
	</body>
</html>
