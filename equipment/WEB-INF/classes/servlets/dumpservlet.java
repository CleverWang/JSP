package servlets;
import javabeans.dumpbean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/*
 *����Ϊ�豸���ϵ�servlet
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
        String dumpReason=request.getParameter("dumpReason");
        String agreeMan=request.getParameter("agreeMan");
        String dumpHandler=request.getParameter("dumpHandler");
        String handleWay=request.getParameter("handleWay");
        String backnews="";
        int internalNum=Integer.parseInt(request.getParameter("internalNum"));

        try {
            con=DriverManager.getConnection(uri);
            // ��ѯ��buy�����Ƿ���ڴ��豸
            String selectcondition = "SELECT * FROM buy WHERE internalNum='" +  internalNum + "'";
            sql=con.prepareStatement(selectcondition);
            rs = sql.executeQuery();
            if (rs.next()) {
                // ������ڱ��У��򽫱��еĴ�������ɾ��
                int num=rs.getInt("num");
                if (num==1) {
                    String deletecondition = "DELETE FROM buy WHERE internalNum='" + internalNum + "'";
                    sql=con.prepareStatement(deletecondition);
                    sql.executeUpdate();
                }

                else {
                    int realnum=num-1;
                    String decrease="UPDATE buy SET num='"+realnum+"'WHERE internalNum='"+internalNum+"'";
                    sql=con.prepareStatement(decrease);
                    sql.executeUpdate();
                }


                // ��������Ϣ����dump����
                String insertcondition="INSERT INTO dump VALUES(?, ?, ?, ?, ?)";
                sql=con.prepareStatement(insertcondition);
                sql.setInt(1,internalNum);
                sql.setString(2,dumpReason);
                sql.setString(3,agreeMan);
                sql.setString(4,dumpHandler);
                sql.setString(5,handleWay);
                int m=sql.executeUpdate();
                if (m!=0) {
                    // ��������Ϣ����bean��
                    dump.setInternalNum(internalNum);
                    dump.setDumpReason(dumpReason);
                    dump.setAgreeMan(agreeMan);
                    dump.setDumpHandler(dumpHandler);
                    dump.setHandleWay(handleWay);
                    dump.setBacknews("�豸���ϳɹ���");
                }

            }
            else {
                backnews="���豸�����ڣ�";
                dump.setBacknews(backnews);
            }
            con.close();
            RequestDispatcher dispatcher=request.getRequestDispatcher("dump.jsp");
            dispatcher.forward(request,response);
        }
        catch (SQLException exp) {
            backnews="�������ݿ�ʧ�ܣ������ԣ�";
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


