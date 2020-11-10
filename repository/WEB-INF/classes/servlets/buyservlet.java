/*
 *buyservlet.java
 *
 *作者：王聪
 *
 *此为实现商品录入功能设计的servlet
 *
 */

package servlets;

import javabeans.buybean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class buyservlet extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // 加载JDBC数据库驱动程序
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e) {}
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = "jdbc:mysql://127.0.0.1:3306/system?" + "user=root&password=&characterEncoding=gb2312";
        Connection con;
        PreparedStatement sql;

        // 创建request周期的javabean
        buybean buy = new buybean();
        request.setAttribute("buy", buy);

        // 从jsp页面获取输入的参数
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

        // 将购入的新设备信息存入system数据库，插入buy表中
        boolean boo = name.length() > 0 && num >= 0 && internalNum >= 0;
        String backnews = "";
        try {
            con = DriverManager.getConnection(uri);// 连接数据库
            String insertcondition = "INSERT INTO buy VALUES(?,?,?,?,?,?,?,?,?,?)";// 插入预处理指令
            sql = con.prepareStatement(insertcondition);

            // 判断信息是否填入完整
            if (boo) {
                sql.setString(1, buyTime);
                sql.setString(2, name);
                sql.setString(3, brand);
                sql.setString(4, model);
                sql.setString(5, type);
                sql.setInt(6, num);
                sql.setInt(7, internalNum);
                sql.setString(8, department);
                sql.setString(9, reciever);
                sql.setString(10, position);
                int m = sql.executeUpdate();

                // 判断信息是否插入成功
                if (m != 0) {
                    backnews = "商品信息录入成功！";
                    buy.setBacknews(backnews);
                    buy.setName(name);
                    buy.setNum(num);
                    buy.setInternalNum(internalNum);
                }
            } else {
                backnews = "信息填写不完整,请重试!";
                buy.setBacknews(backnews);
            }
            con.close();
        } catch (SQLException exp) {
            backnews = "连接数据库失败，请重试！";
            buy.setBacknews(backnews);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("buy.jsp");
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

