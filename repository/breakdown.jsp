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
		  <h1><font color="white">��Ʒ�˻�ϵͳ</font><h1>
        </div>
      </header>
	 <div>
	 <br>
	  <%String operationMan=(String)session.getAttribute("operationMan");
	  if(operationMan==null){%>
	  <h3><font color=red>���ȵ�¼��</font></h3>
	  <%}
	 else if(operationMan.equals("student")) {%>
      <form class="" action="breakdownservlet" method="post">
		<p>��Ʒ���:</p><input type="text" name="internalNum" value="" placeholder="Internal Number"><br>
		<p>�ͻ���:</p><input type="text" name="man" value="" placeholder="Costumer Name"><br>
		<p>�˻�����:</p><input type="text" name="breakdownReason" value="" placeholder="Reason"><br>
        <button type="submit" name="button">�ύ</button>
		<h4><a href="index.jsp">��ҳ</a></h4>
      </form> 
	  <center><font color=red>�豸���ϴ�������Ϣ��<jsp:getProperty name="breakdown" property="backnews"/></font></center>
	  <%}
	  else if(operationMan.equals("admin")){%>
      <form class="" action="breakdownservlet" method="post">
		<p>��Ʒ���:</p><input type="text" name="internalNum" value="" placeholder="Internal Number"><br>
		<p>�˻���:</p><input type="text" name="man" value="" placeholder="Costumer Name"><br>
		<p>����ʱ��:</p><input type="text" name="fixTime" value="" placeholder="Return Time"><br>
		<p>������Ա:</p><input type="text" name="fixMan" value="" placeholder="Handler"><br>
		<p>�˻����:</p><input type="text" name="fixResult" value="" placeholder="Return Result"><br>
		<p>�˻�����:</p><input type="text" name="fixBill" value="" placeholder="Disposing Way"><br>
        <button type="submit" name="button">�ύ</button>
		<h4><a href="index.jsp">��ҳ</a></h4>
      </form> 
	  <center><font color=red>��Ʒ�˻�ϵͳ������Ϣ��<jsp:getProperty name="breakdown" property="backnews"/></font></center>
      <%}%> 
     <div>
	</body>
</html>