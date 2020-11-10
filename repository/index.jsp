<%@ page contentType="text/html;charset=gb2312"%>
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/index.css">
  </head>
  <body background="image/bg.jpg">
  <%String backnews="ÎÞ";
  session.setAttribute("backnews",backnews);%>
      <div class="text-center">
      <header>
         <div class="sign" style="text-align: right">
          <a href="signup.jsp">×¢²á</a> | <a href="login.jsp">µÇÂ¼</a>
        </div>
      </header>
      <br></div>
		<p class="text-center">
		<img alt="" src="image/head.png" width="1000" height="150"></p>
  	<p class="text-center">
	<a href="buy.jsp"><img alt="" src="image/buy.png" width="300" height="150"></a>
	<a href="dump.jsp"><img alt="" src="image/dump.png" width="300" height="150"></a></p>
	<p class="text-center">
	<a href="lend.jsp"><img alt="" src="image/lend.png" width="300" height="150"></a>
	<a href="breakdown.jsp"><img alt="" src="image/breakdown.png" width="300" height="150"></a></p>
	<p class="text-center">
	<a href="all.jsp"><img alt="" src="image/all.png" width="600" height="100"></a></p>
	<p class="text-center">
	<a href="return.jsp"><img alt="" src="image/return.png" width="600" height="100"></a></p>
  </body>
</html>
