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
		 <h1><center><font color="white">��Ʒɾ��ϵͳ</font></center><h1>
       </div>
     </header>
	 <div>
	 <br>
	 <%
	 String operationMan=(String)session.getAttribute("operationMan");
	 if(operationMan==null){%>
	  <center><h3><font color=red>���ȵ�¼��</font></h3></center>
	  <%}
	  else if(operationMan.equals("admin")) {%>
      <form class="" action="lendservlet" method="post">
	    <p>��Ʒ���:</p><input type="text" name="internalNum" value="" placeholder="Internal Number"><br>		
        <p>ɾ������:</p><input type="text" name="num" value="" placeholder="Delete Amount"><br>
        <p>ɾ��ԭ��:</p><input type="text" name="lendDepart" value="" placeholder="Delete Reason"><br>
		<p>ɾ����Ա:</p><input type="text" name="handler" value="" placeholder="Handler"><br>
        <p>��Ʒȥ��:</p><input type="text" name="use" value="" placeholder="Disposing Way"><br>
		<p>ɾ������:</p><input type="text" name="intendedDate" value="" placeholder="Delete Date"><br>
        <button type="submit" name="button">�ύ</button>
		<h4><a href="index.jsp">��ҳ</a></h4>
      </form>   
     <div>
	 <div><center><font color=red>
	ɾ��ϵͳ������Ϣ��<jsp:getProperty name="lend" property="backnews"/></font>
	  <%}
	  else{%>
	  <center><h3><font color=red>��û��ʹ�øù��ܵ�Ȩ�ޣ�</font></h3></center>
	  <%}%>
	</body>
</html>
