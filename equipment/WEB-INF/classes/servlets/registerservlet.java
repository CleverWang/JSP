/*
 *registerservlet.java
 *
 *���ߣ�����
 *
 *��Ϊʵ���û�ע�Ṧ����Ƶ�servlet
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
		   PreparedStatement sql;
		   
		   //����request���ڵ�javabean
		   registerbean register=new registerbean();
		   request.setAttribute("register",register);
		   
		   //��jspҳ���ȡ����Ĳ���
		   request.setCharacterEncoding("gb2312");
		   String name=request.getParameter("name");
		   String password=request.getParameter("password");
		   String confirm=request.getParameter("confirm");
		   
		   //�ж���������������Ƿ�һ��
		   if(!password.equals(confirm)){
			   register.setBacknews("�������벻ͬ��ע��ʧ�ܣ������ԡ�");
			   RequestDispatcher dispatcher=request.getRequestDispatcher("signup.jsp");
			   dispatcher.forward(request,response);
			   return;
		   }
		   
		   //�ж�������û����Ƿ�Ϸ����������Ϣ�Ƿ�����
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
			   String insertcondition="INSERT INTO users VALUES(?,?)";//��users���в������û���¼
			   sql=con.prepareStatement(insertcondition);
			   if(boo){
				   sql.setString(1,name);
				   sql.setString(2,password);
				   int m=sql.executeUpdate();
				   if(m!=0){
					   backnews="ע��ɹ���";
					   register.setBacknews(backnews);
					   register.setName(name);
				   }
			   }
			   else{
				   backnews="��Ϣ��д�����������ֲ��Ϸ��������ԡ�";
				   register.setBacknews(backnews);
			   }
			   con.close();
		   }
		   catch(SQLException exp){
			   backnews="���û�����ʹ�ã������ԡ�";
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