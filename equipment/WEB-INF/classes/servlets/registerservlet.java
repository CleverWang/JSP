/*
 *registerservlet.java
 *
 *作者：王聪
 *
 *此为实现用户注册功能设计的servlet
 *
 */

package servlets;
import javabeans.registerbean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class registerservlet extends HttpServlet
{  
   public void init(ServletConfig config) throws ServletException{
	   super.init(config); 
	   
	   //加载JDBC数据库驱动程序
	   try{
		   Class.forName("com.mysql.jdbc.Driver");
	   }
	   catch(Exception e){}
	   }
	
   public void doPost(HttpServletRequest request,
       HttpServletResponse response) throws ServletException,IOException{
		   String uri="jdbc:mysql://127.0.0.1:3306/system?"+"user=root&password=&characterEncoding=gb2312";
		   Connection con;
		   PreparedStatement sql;
		   
		   //创建request周期的javabean
		   registerbean register=new registerbean();
		   request.setAttribute("register",register);
		   
		   //从jsp页面获取输入的参数
		   request.setCharacterEncoding("gb2312");
		   String name=request.getParameter("name");
		   String password=request.getParameter("password");
		   String confirm=request.getParameter("confirm");
		   
		   //判断两次输入的密码是否一致
		   if(!password.equals(confirm)){
			   register.setBacknews("两次密码不同，注册失败！请重试。");
			   RequestDispatcher dispatcher=request.getRequestDispatcher("signup.jsp");
			   dispatcher.forward(request,response);
			   return;
		   }
		   
		   //判断输入的用户名是否合法与输入的信息是否完整
		   boolean b=true;
		   for(int i=0;i<name.length();++i){
			   char c=name.charAt(i);
			   if(!(c<='z'&&c>='a'||c<='Z'&&c>='A'||c<='9'&&c>='0'||c=='_'))
				   b=false;
		   }
		   boolean boo=name.length()>0&&password.length()>0&&b;
		   
		   String backnews="";
		   try{
			   con=DriverManager.getConnection(uri);
			   String insertcondition="INSERT INTO users VALUES(?,?)";//向users表中插入新用户记录
			   sql=con.prepareStatement(insertcondition);
			   if(boo){
				   sql.setString(1,name);
				   sql.setString(2,password);
				   int m=sql.executeUpdate();
				   if(m!=0){
					   backnews="注册成功！";
					   register.setBacknews(backnews);
					   register.setName(name);
				   }
			   }
			   else{
				   backnews="信息填写不完整或名字不合法！请重试。";
				   register.setBacknews(backnews);
			   }
			   con.close();
		   }
		   catch(SQLException exp){
			   backnews="该用户名已使用，请重试。";
			   register.setBacknews(backnews);
		   }
		   RequestDispatcher dispatcher=request.getRequestDispatcher("signup.jsp");
		   dispatcher.forward(request,response);
	   }
	   
   public void doGet(HttpServletRequest request,
       HttpServletResponse response) throws ServletException,IOException{
		   doPost(request,response);
	}
}