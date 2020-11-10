/*
 *lendservlet.java
 *
 *���ߣ�����
 *
 *��Ϊʵ���豸ת�蹦����Ƶ�servlet
 *
 */

package servlets;
import javabeans.lendbean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class lendservlet extends HttpServlet
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
		   Statement findsql;
		   
		   //����request���ڵ�javabean
		   lendbean lend=new lendbean();
		   request.setAttribute("lend",lend);
		   
		   //��jspҳ���ȡ����Ĳ���
		   request.setCharacterEncoding("gb2312");
		   String lendDepart=request.getParameter("lendDepart");
		   String handler=request.getParameter("handler");
		   String use=request.getParameter("use");
		   String intendedDate=request.getParameter("intendedDate");
		   int num=Integer.parseInt(request.getParameter("num"));
		   int internalNum=Integer.parseInt(request.getParameter("internalNum"));
		   
		   //���������ת���豸�ڲ���ź�ת����������ѯbuy�����Ƿ��и��豸����豸�����Ƿ�ת�裬����ǣ���ת����Ϣ����lend����
		   boolean boo=(num>=0)&&(internalNum>=0)&&(lendDepart.length()>0)&&(use.length()>0)&&(intendedDate.length()>0);
		   try{
			   con=DriverManager.getConnection(uri);//�������ݿ�
			   String condition="SELECT * FROM buy WHERE internalNum='"+internalNum+"'";//��ѯbuy�����Ƿ��и��豸
			   String insertcondition="INSERT INTO lend VALUES(?,?,?,?,?,?)";//��ת����Ϣ����lend����
			   sql=con.prepareStatement(insertcondition);
			   findsql=con.createStatement();
			   String backnews="";
			   
			   //�ж�ת����Ϣ�Ƿ���������
			   if(boo){
				   ResultSet rs=sql.executeQuery(condition);
				   
				   //�ж�buy�����Ƿ��и��豸
				   if(rs.next()){
					   int findnum=rs.getInt("num");
					   
					   //�жϸ��豸�����Ƿ�ת��
					   if(num<=findnum){
						   int realnum=findnum-num;
						   findsql.executeUpdate("UPDATE buy SET num='"+realnum+"'WHERE internalNum='"+internalNum+"'");
						   //ת��󣬸���buy���и��豸��ʵ��ʣ������
						   
						   //����ת����Ϣ��lend����
						   sql.setString(1,lendDepart);
				           sql.setString(2,handler);
						   sql.setString(3,use);
						   sql.setString(4,intendedDate);
						   sql.setInt(5,internalNum);
						   sql.setInt(6,num);
				           int m=sql.executeUpdate();
				           if(m!=0){
							   backnews="ת��ɹ���";
							   lend.setBacknews(backnews);
						   }
					   }
					   else{
						   backnews="�豸��������������������ת��������";
						   lend.setBacknews(backnews);
					   }
				   }
				   else{
					   backnews="��������豸�����ڣ���ȷ���豸�ڲ���ź���ת�裡";
					   lend.setBacknews(backnews);
				   }
			   }
			   else{
				   backnews="���������ת����Ϣ�Լ��豸�ڲ���ź�ת��������";
				   lend.setBacknews(backnews);
			   }
			   con.close();
			   RequestDispatcher dispatcher=request.getRequestDispatcher("lend.jsp");
		       dispatcher.forward(request,response);
		   }
		   catch(SQLException exp){
		       String backnews="���ݿ�����ʧ�ܣ�"+exp;
			   lend.setBacknews(backnews);
			   RequestDispatcher dispatcher=request.getRequestDispatcher("lend.jsp");
		       dispatcher.forward(request,response);
		   }
	   }
	   
   public void doGet(HttpServletRequest request,
       HttpServletResponse response) throws ServletException,IOException{
		   doPost(request,response);
	   }
}