/*
 *buyservlet.java
 *
 *���ߣ�����
 *
 *��Ϊʵ����Ʒ¼�빦����Ƶ�servlet
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

        // ����JDBC���ݿ���������
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e) {}
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = "jdbc:mysql://127.0.0.1:3306/system?" + "user=root&password=&characterEncoding=gb2312";
        Connection con;
        PreparedStatement sql;

        // ����request���ڵ�javabean
        buybean buy = new buybean();
        request.setAttribute("buy", buy);

        // ��jspҳ���ȡ����Ĳ���
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

        // ����������豸��Ϣ����system���ݿ⣬����buy����
        boolean boo = name.length() > 0 && num >= 0 && internalNum >= 0;
        String backnews = "";
        try {
            con = DriverManager.getConnection(uri);// �������ݿ�
            String insertcondition = "INSERT INTO buy VALUES(?,?,?,?,?,?,?,?,?,?)";// ����Ԥ����ָ��
            sql = con.prepareStatement(insertcondition);

            // �ж���Ϣ�Ƿ���������
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

                // �ж���Ϣ�Ƿ����ɹ�
                if (m != 0) {
                    backnews = "��Ʒ��Ϣ¼��ɹ���";
                    buy.setBacknews(backnews);
                    buy.setName(name);
                    buy.setNum(num);
                    buy.setInternalNum(internalNum);
                }
            } else {
                backnews = "��Ϣ��д������,������!";
                buy.setBacknews(backnews);
            }
            con.close();
        } catch (SQLException exp) {
            backnews = "�������ݿ�ʧ�ܣ������ԣ�";
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

