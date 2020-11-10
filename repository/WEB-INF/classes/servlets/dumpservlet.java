package servlets;
import javabeans.dumpbean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/*
 *此类为商品修改的servlet
 */
public class dumpservlet extends HttpServlet
{
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e) {}
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,IOException {
        String uri="jdbc:mysql://127.0.0.1:3306/system?"+"user=root&password=&characterEncoding=gb2312";
        Connection con;
        PreparedStatement sql;
        ResultSet rs;

        dumpbean dump = new dumpbean();
        request.setAttribute("dump",dump);

        request.setCharacterEncoding("gb2312");
        String buyTime = request.getParameter("buyTime");
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String type = request.getParameter("type");
        String department = request.getParameter("department");
        String reciever = request.getParameter("reciever");
        String position = request.getParameter("position");
        int num = Integer.parseInt(request.getParameter("num"));
        int internalNum = Integer.parseInt(request.getParameter("internalNum"));
        String backnews="";

        try {
            con=DriverManager.getConnection(uri);
            // 查询在buy表中是否存在此商品
            String selectcondition = "SELECT * FROM buy WHERE internalNum='" +  internalNum + "'";
            sql=con.prepareStatement(selectcondition);
            rs = sql.executeQuery();
            if (rs.next()) {
                String update="UPDATE buy SET num='"+num+"',buyTime='"+buyTime+"',name='"+name+"',brand='"+brand+"',model='"+model+"',type='"+type+"',department='"+department+"',reciever='"+reciever+"',position='"+position+"' WHERE internalNum='"+internalNum+"'";
                sql=con.prepareStatement(update);
                int m=sql.executeUpdate();
                
                if (m!=0) {
                    dump.setBacknews("商品信息修改成功！");
                }
				else{
					dump.setBacknews("商品信息修改失败，请重试！");
				}

            }
            else {
                backnews="该商品不存在！";
                dump.setBacknews(backnews);
            }
            con.close();
            RequestDispatcher dispatcher=request.getRequestDispatcher("dump.jsp");
            dispatcher.forward(request,response);
        }
        catch (SQLException exp) {
            backnews="连接数据库失败，请重试！"+exp.toString();
            dump.setBacknews(backnews);
            RequestDispatcher dispatcher=request.getRequestDispatcher("dump.jsp");
            dispatcher.forward(request,response);
        }

    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,IOException {
        doPost(request,response);
    }
}


