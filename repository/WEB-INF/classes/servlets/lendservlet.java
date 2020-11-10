/*
 *lendservlet.java
 *
 *作者：王聪
 *
 *此为实现商品删除功能设计的servlet
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
		   
		   //根据输入的内部编号和转借数量，查询buy表中是否有该设备与该设备数量是否够删除，如果是，将删除信息存入lend表中
		   boolean boo=(num>=0)&&(internalNum>=0)&&(lendDepart.length()>0)&&(use.length()>0)&&(intendedDate.length()>0);
		   try{
			   con=DriverManager.getConnection(uri);//连接数据库
			   String condition="SELECT * FROM buy WHERE internalNum='"+internalNum+"'";//查询buy表中是否有该商品
			   String insertcondition="INSERT INTO lend VALUES(?,?,?,?,?,?)";//将转借信息存入lend表中
			   sql=con.prepareStatement(insertcondition);
			   findsql=con.createStatement();
			   String backnews="";
			   
			   //判断删除信息是否输入完整
			   if(boo){
				   ResultSet rs=sql.executeQuery(condition);
				   
				   //判断buy表中是否有该商品
				   if(rs.next()){
					   int findnum=rs.getInt("num");
					   
					   //判断该商品数量是否够删除
					   if(num<=findnum){
						   int realnum=findnum-num;
						   if(realnum!=0)
						       findsql.executeUpdate("UPDATE buy SET num='"+realnum+"'WHERE internalNum='"+internalNum+"'");
						       //删除后，更新buy表中该设备的实际剩余数量
						   else
							   findsql.executeUpdate("DELETE FROM buy WHERE internalNum='" + internalNum + "'");
						   
						   //存入删除信息到lend表中
						   sql.setString(1,lendDepart);
				           sql.setString(2,handler);
						   sql.setString(3,use);
						   sql.setString(4,intendedDate);
						   sql.setInt(5,internalNum);
						   sql.setInt(6,num);
				           int m=sql.executeUpdate();
				           if(m!=0){
							   backnews="删除成功！";
							   lend.setBacknews(backnews);
						   }
					   }
					   else{
						   backnews="输入的删除数量有误！";
						   lend.setBacknews(backnews);
					   }
				   }
				   else{
					   backnews="您输入的商品不存在，请确认商品编号后再删除！";
					   lend.setBacknews(backnews);
				   }
			   }
			   else{
				   backnews="请输入相关商品信息以及商品编号和删除数量！";
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