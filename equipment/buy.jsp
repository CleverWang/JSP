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
		  <h1><center><font color="white">�豸����Ǽ�ϵͳ</font></center><h1>
        </div>
      </header>
	 <div>
	 <br>
	 <%
	 String operationMan=(String)session.getAttribute("operationMan");
	 if(operationMan==null){%>
	  <h3><font color=red>���ȵ�¼��</font></h3>
	  <%}
	 else if(operationMan.equals("admin")) {%>
      <form class="" action="buyservlet" method="post">
        <p>����:</p><input type="text" name="name" value="" placeholder="Name"><br>
        <p>����ʱ��:</p><input type="text" name="buyTime" value="" placeholder="Buy Time"><br>
		<p>Ʒ��:</p><input type="text" name="brand" value="" placeholder="Brand"><br>
		<p>�ͺ�:</p><input type="text" name="model" value="" placeholder="Model"><br>
		<p>����:</p><input type="text" name="type" value="" placeholder="Type"><br>
		<p>����:</p><input type="text" name="num" value="" placeholder="Number"><br>
		<p>�ڲ����:</p><input type="text" name="internalNum" value="" placeholder="Internal Number"><br>
		<p>��������:</p><input type="text" name="department" value="" placeholder="Department"><br>
	    <p>������:</p><input type="text" name="reciever" value="" placeholder="Reciever"><br>
		<p>���λ��:</p><input type="text" name="position" value="" placeholder="Position"><br>		
        <button type="submit" name="button">�ύ</button>
		<h4><a href="index.jsp">��ҳ</a></h4>
      </form>
	  <center><font color=red>�豸¼�뷴����Ϣ��<jsp:getProperty name="buy" property="backnews"/></font></center>
      <%}
	  else{%>
      	<h3><font color=red>��û��ʹ�øù��ܵ�Ȩ�ޣ�</font></h3>
	  <%}%>
     
     <div>
	 
	</body>
</html>
