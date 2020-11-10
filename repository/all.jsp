<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>

<%
//变量声明
Connection sqlCon; //数据库连接对象
Statement sqlStmt; //SQL语句对象
ResultSet sqlRst;  //结果集对象

String strCon;    //数据库连接字符串
String strSQL;    //SQL语句

int intPageSize;           //一页显示的记录数
int intRowCount;           //记录总数
int intPageCount;          //总页数
int intPage;               //待显示页码
String strPage;

int i;
intPageSize = 5;//设置一页显示的记录数
strPage = request.getParameter("page");//取得待显示页码
if(strPage==null){//表明在QueryString中没有page这一个参数，此时显示第一页数据
   intPage = 1;
}
else{//将字符串转换成整型
   intPage = java.lang.Integer.parseInt(strPage);
   if(intPage<1) intPage = 1;
}

//装载JDBC驱动程序
try{
	Class.forName("com.mysql.jdbc.Driver");
   }
catch(Exception e){}

strCon = "jdbc:mysql://127.0.0.1:3306/system?"+"user=root&password=&characterEncoding=gb2312";//设置数据库连接字符串
sqlCon = DriverManager.getConnection(strCon);//连接数据库
sqlStmt = sqlCon.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);//创建一个可以滚动的只读的SQL语句对象
strSQL = "select internalNum,name,brand,model,type,department,position,num from buy";//准备SQL语句
sqlRst = sqlStmt.executeQuery(strSQL);//执行SQL语句并获取结果集

//获取记录总数
sqlRst.last();
intRowCount = sqlRst.getRow();

//记算总页数
intPageCount = (intRowCount+intPageSize-1) / intPageSize;

//调整待显示的页码
if(intPage>intPageCount) intPage = intPageCount;
%>

<html>
<head>
<meta charset="utf-8">
    <title></title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/all.css">
</head>

<body>
<div class="text-center">
      <header>
        <div class="sign" style="text-align: left">
		  <h1><center><font color="white">商品信息查询系统</font></center><h1>
        </div>
      </header>
	 <div><br>
<%String operationMan=(String)session.getAttribute("operationMan");
	 if(operationMan==null){%>
	  <center><h3><font color=red>请先登录！</font></h3></center>
   <%}
   else {%>


<form action="" method="post">
<table>
<tr>
   <th>商品编号</th>
   <th>商品名称</th>
   <th>商品品牌</th>
   <th>商品型号</th>
   <th>商品类型</th>
   <th>生产厂商</th>
   <th>存放位置</th>
   <th>商品数量</th>
</tr>
<%
if(intPageCount>0){
   //将记录指针定位到待显示页的第一条记录上
   sqlRst.absolute((intPage-1) * intPageSize + 1);

   //显示数据
   i = 0;
   while(i<intPageSize && !sqlRst.isAfterLast()){
 %>
<tr>
   <td><%=sqlRst.getInt(1)%></td>
   <td><%=sqlRst.getString(2)%></td>
   <td><%=sqlRst.getString(3)%></td>
   <td><%=sqlRst.getString(4)%></td>
   <td><%=sqlRst.getString(5)%></td>
   <td><%=sqlRst.getString(6)%></td>
   <td><%=sqlRst.getString(7)%></td>
   <td><%=sqlRst.getInt(8)%></td>
</tr>
<%
      sqlRst.next();
      i++;
   }
}
%>
</table>
<h4><a href="index.jsp">首页</a></h4>
</form>
<center>
第<%=intPage%>页(共<%=intPageCount%>页)     <%if(intPage<intPageCount){%><a href="all.jsp?page=<%=intPage+1%>">下一页</a><%}%><%if(intPage>1){%><a href="all.jsp?page=<%=intPage-1%>">上一页</a><%}%>
   <%}%></center>
   <div>
</body>
</html>

<%
//关闭结果集
sqlRst.close();

//关闭SQL语句对象
sqlStmt.close();

//关闭数据库
sqlCon.close();
%>