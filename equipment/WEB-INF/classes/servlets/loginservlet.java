/*
 *loginservlet.java
 *
 *���ߣ�����
 *
 *��Ϊʵ���û���¼������Ƶ�servlet
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
	   
	   //����JDBC���ݿ��������� 
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
		   
		   //��jspҳ���ȡ����Ĳ���
		   request.setCharacterEncoding("gb2312");
		   String name=request.getParameter("name");
		   String password=request.getParameter("password");
		   
		   boolean boo=name.length()>0&&password.length()>0;
		   try{
			   con=DriverManager.getConnection(uri);
			   String condition="select * from users where name='"+name+"'and password='"+password+"'";//��ѯusers�����Ƿ��и��û�
			   sql=con.createStatement();
			   String backnews="��";
			   HttpSession session=request.getSession(true);
			   
			   //�ж�������Ϣ�Ƿ�����
			   if(boo){
				   ResultSet rs=sql.executeQuery(condition);
				   
				   //�ж�users�����Ƿ��и��û�
				   if(rs.next()){
					   loginbean login=null;
					   session.setAttribute("backnews",backnews);
					   
					   //�ж��û��Ƿ��Ѿ���¼���Ƿ��ǹ���Ա��¼
					   try{
						   login=(loginbean)session.getAttribute("login");
						   if(login==null){
							   //����session���ڵ�javabean
							   login=new loginbean();
							   session.setAttribute("login",login);
							   login=(loginbean)session.getAttribute("login");
						   }
						   if(login.getName().equals(name)){
							   login.setBacknews(name+"�Ѿ���¼��");
							   login.setName(name);
							   response.sendRedirect("login.jsp");
						   }
						   else{
							   login.setBacknews(name+"��½�ɹ���");
							   login.setName(name);
							   //����ǹ���Ա��¼��session��operationMan����Ϊadmin���������ó�student
							   if(name.equals("admin"))
								   session.setAttribute("operationMan",name);
							   else session.setAttribute("operationMan","student");
							   response.sendRedirect("login.jsp");
							}
						  
					   }
					   catch(Exception e){
						   login=new loginbean();
						   session.setAttribute("login",login);
						   login.setBacknews(name+"��½�ɹ���");
						   login.setName(name);
						   if(name.equals("admin"))
								   session.setAttribute("operationMan",name);
						   else session.setAttribute("operationMan","student");
						   response.sendRedirect("login.jsp");
					   }
				   }
				   else{
					   backnews="��������û��������ڻ��������";
					   session.setAttribute("backnews",backnews);
					   response.sendRedirect("login.jsp");
				   }
				  
			   }
			   else{
				   backnews="��������û��������룡";
				   session.setAttribute("backnews",backnews);
				   response.sendRedirect("login.jsp");
			   }
			   con.close();
		   }
		   catch(SQLException exp){
			   HttpSession session=request.getSession(true);
		       String backnews="���ݿ�����ʧ�ܣ�"+exp;
			   session.setAttribute("backnews",backnews);
			   response.sendRedirect("login.jsp");
		   }
	   }
	   
   public void doGet(HttpServletRequest request,
       HttpServletResponse response) throws ServletException,IOException{
		   doPost(request,response);
	   }
}