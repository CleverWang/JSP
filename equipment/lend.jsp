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
		 <h1><center><font color="white">�豸ת������ϵͳ</font></center><h1>
       </div>
     </header>
	 <div>
	 <br>
	 <%
	 String operationMan=(String)session.getAttribute("operationMan");
	 if(operationMan==null){%>
	  <center><h3><font color=red>���ȵ�¼��</font></h3></center>
	  <%}
	  else {%>
      <form class="" action="lendservlet" method="post">
	    <p>�ڲ����:</p><input type="text" name="internalNum" value="" placeholder="Internal Number"><br>		
        <p>��������:</p><input type="text" name="num" value="" placeholder="Lend Number"><br>
        <p>���ò���:</p><input type="text" name="lendDepart" value="" placeholder="Lend Department"><br>
		<p>������Ա:</p><input type="text" name="handler" value="" placeholder="Handler"><br>
        <p>��;:</p><input type="text" name="use" value="" placeholder="Usage"><br>
		<p>�⻹����:</p><input type="text" name="intendedDate" value="" placeholder="Intended Date"><br>
        <button type="submit" name="button">�ύ</button>
		<h4><a href="index.jsp">��ҳ</a></h4>
      </form>   
     <div>
	 <div><center><font color=red>
	ת�跴����Ϣ��<jsp:getProperty name="lend" property="backnews"/></font>
	  <%}%>
	</body>
</html>
