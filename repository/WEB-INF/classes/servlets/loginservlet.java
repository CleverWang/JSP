/*
 *loginservlet.java
 *
 *作者：王聪
 *
 *此为实现用户登录功能设计的servlet
 *
 */

package servlets;
import javabeans.loginbean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class loginservlet extends HttpServlet
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
		   Statement sql;
		   
		   //从jsp页面获取输入的参数
		   request.setCharacterEncoding("gb2312");
		   String name=request.getParameter("name");
		   String password=request.getParameter("password");
		   
		   boolean boo=name.length()>0&&password.length()>0;
		   try{
			   con=DriverManager.getConnection(uri);
			   String condition="select * from users where name='"+name+"'and password='"+password+"'";//查询users表中是否有该用户
			   sql=con.createStatement();
			   String backnews="无";
			   HttpSession session=request.getSession(true);
			   
			   //判断输入信息是否完整
			   if(boo){
				   ResultSet rs=sql.executeQuery(condition);
				   
				   //判断users表中是否有该用户
				   if(rs.next()){
					   loginbean login=null;
					   session.setAttribute("backnews",backnews);
					   
					   //判断用户是否已经登录与是否是管理员登录
					   try{
						   login=(loginbean)session.getAttribute("login");
						   if(login==null){
							   //创建session周期的javabean
							   login=new loginbean();
							   session.setAttribute("login",login);
							   login=(loginbean)session.getAttribute("login");
						   }
						   if(login.getName().equals(name)){
							   login.setBacknews(name+"已经登录！");
							   login.setName(name);
							   response.sendRedirect("login.jsp");
						   }
						   else{
							   login.setBacknews(name+"登陆成功！");
							   login.setName(name);
							   //如果是管理员登录，session中operationMan设置为admin，否则设置成student
							   if(name.equals("admin"))
								   session.setAttribute("operationMan",name);
							   else session.setAttribute("operationMan","student");
							   response.sendRedirect("login.jsp");
							}
						  
					   }
					   catch(Exception e){
						   login=new loginbean();
						   session.setAttribute("login",login);
						   login.setBacknews(name+"登陆成功！");
						   login.setName(name);
						   if(name.equals("admin"))
								   session.setAttribute("operationMan",name);
						   else session.setAttribute("operationMan","student");
						   response.sendRedirect("login.jsp");
					   }
				   }
				   else{
					   backnews="您输入的用户名不存在或密码错误！";
					   session.setAttribute("backnews",backnews);
					   response.sendRedirect("login.jsp");
				   }
				  
			   }
			   else{
				   backnews="请输入的用户名和密码！";
				   session.setAttribute("backnews",backnews);
				   response.sendRedirect("login.jsp");
			   }
			   con.close();
		   }
		   catch(SQLException exp){
			   HttpSession session=request.getSession(true);
		       String backnews="数据库连接失败："+exp;
			   session.setAttribute("backnews",backnews);
			   response.sendRedirect("login.jsp");
		   }
	   }
	   
   public void doGet(HttpServletRequest request,
       HttpServletResponse response) throws ServletException,IOException{
		   doPost(request,response);
	   }
}