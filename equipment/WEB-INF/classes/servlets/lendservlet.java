/*
 *lendservlet.java
 *
 *作者：王聪
 *
 *此为实现设备转借功能设计的servlet
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
		   Statement findsql;
		   
		   //创建request周期的javabean
		   lendbean lend=new lendbean();
		   request.setAttribute("lend",lend);
		   
		   //从jsp页面获取输入的参数
		   request.setCharacterEncoding("gb2312");
		   String lendDepart=request.getParameter("lendDepart");
		   String handler=request.getParameter("handler");
		   String use=request.getParameter("use");
		   String intendedDate=request.getParameter("intendedDate");
		   int num=Integer.parseInt(request.getParameter("num"));
		   int internalNum=Integer.parseInt(request.getParameter("internalNum"));
		   
		   //根据输入的转借设备内部编号和转借数量，查询buy表中是否有该设备与该设备数量是否够转借，如果是，将转借信息存入lend表中
		   boolean boo=(num>=0)&&(internalNum>=0)&&(lendDepart.length()>0)&&(use.length()>0)&&(intendedDate.length()>0);
		   try{
			   con=DriverManager.getConnection(uri);//连接数据库
			   String condition="SELECT * FROM buy WHERE internalNum='"+internalNum+"'";//查询buy表中是否有该设备
			   String insertcondition="INSERT INTO lend VALUES(?,?,?,?,?,?)";//将转借信息存入lend表中
			   sql=con.prepareStatement(insertcondition);
			   findsql=con.createStatement();
			   String backnews="";
			   
			   //判断转借信息是否输入完整
			   if(boo){
				   ResultSet rs=sql.executeQuery(condition);
				   
				   //判断buy表中是否有该设备
				   if(rs.next()){
					   int findnum=rs.getInt("num");
					   
					   //判断该设备数量是否够转借
					   if(num<=findnum){
						   int realnum=findnum-num;
						   findsql.executeUpdate("UPDATE buy SET num='"+realnum+"'WHERE internalNum='"+internalNum+"'");
						   //转借后，更新buy表中该设备的实际剩余数量
						   
						   //存入转借信息到lend表中
						   sql.setString(1,lendDepart);
				           sql.setString(2,handler);
						   sql.setString(3,use);
						   sql.setString(4,intendedDate);
						   sql.setInt(5,internalNum);
						   sql.setInt(6,num);
				           int m=sql.executeUpdate();
				           if(m!=0){
							   backnews="转借成功！";
							   lend.setBacknews(backnews);
						   }
					   }
					   else{
						   backnews="设备数量不够，请重新输入转借数量！";
						   lend.setBacknews(backnews);
					   }
				   }
				   else{
					   backnews="您输入的设备不存在，请确认设备内部编号后再转借！";
					   lend.setBacknews(backnews);
				   }
			   }
			   else{
				   backnews="请输入相关转借信息以及设备内部编号和转借数量！";
				   lend.setBacknews(backnews);
			   }
			   con.close();
			   RequestDispatcher dispatcher=request.getRequestDispatcher("lend.jsp");
		       dispatcher.forward(request,response);
		   }
		   catch(SQLException exp){
		       String backnews="数据库连接失败："+exp;
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