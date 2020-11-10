package servlets;
import javabeans.breakdownbean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/*
 *����Ϊ�豸���ϵ�servlet
 */
public class breakdownservlet extends HttpServlet
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

        breakdownbean breakdown = new breakdownbean();
        request.setAttribute("breakdown",breakdown);

        request.setCharacterEncoding("gb2312");

        String breakdownReason=request.getParameter("breakdownReason");
        String fixTime=request.getParameter("fixTime");
        String fixMan=request.getParameter("fixMan");
        String fixResult=request.getParameter("fixResult");
        String fixBill=request.getParameter("fixBill");
        String backnews="";

        int internalNum=Integer.parseInt(request.getParameter("internalNum"));

        // �õ����в�������
        HttpSession session=request.getSession(true);
        String operationMan=(String)session.getAttribute("operationMan");

        try {
            con=DriverManager.getConnection(uri);
            // ��ѯ��lend�����Ƿ���ڴ��豸
            if (operationMan.equals("student")) {
                String selectcondition = "SELECT * FROM lend WHERE internalNum='"+internalNum+"'";
                sql=con.prepareStatement(selectcondition);
                rs = sql.executeQuery();
                if (rs.next()) {
                    // ������ڱ�lend�У�������ѧ��������,��ִ�б���ά���¼�
                    String insertcondition="INSERT INTO breakdown VALUES(?,?,?,?,?,?)";
                    sql=con.prepareStatement(insertcondition);
                    sql.setInt(1,internalNum);
                    sql.setString(2,breakdownReason);
                    sql.setString(3,"");
                    sql.setString(4,"");
                    sql.setString(5,"");
                    sql.setString(6,"");
                    int m=sql.executeUpdate();
                    if (m!=0) {
                        // ��ά�ޱ������bean��
                        breakdown.setInternalNum(internalNum);
                        breakdown.setBreakdown(breakdownReason);
                        breakdown.setBacknews("�豸ά���걨�ɹ���");
                    }

                }
                else {
                    backnews="���豸�����ڣ�";
                    breakdown.setBacknews(backnews);
                }
            }

            else {
                String selectcondition1 = "SELECT * FROM breakdown WHERE internalNum='"+internalNum+"' AND breakdownReason='"+breakdownReason+"'";
                sql=con.prepareStatement(selectcondition1);
                rs = sql.executeQuery();
                if (rs.next()) {

                    // ������ڱ�breakdown�У������ǹ���Ա������,��ִ��ά���¼�
                    // ����ά��ʱ�䡢ά���ˡ�ά�޽����ά�޷���
                    String updatecondition="UPDATE breakdown SET fixTime='" + fixTime +
                                           "',fixMan='" + fixMan + "',fixResult='" + fixResult +
                                           "',fixBill='" + fixBill + "' WHERE internalNum='" + internalNum + "' AND breakdownReason='"+breakdownReason+"'";
                    sql=con.prepareStatement(updatecondition);
                    int m = sql.executeUpdate();
                    if (m!=0) {
                        // ��ά�ޱ������bean��
                        breakdown.setFixTime(fixTime);
                        breakdown.setFixMan(fixMan);
                        breakdown.setFixResult(fixResult);
                        breakdown.setFixBill(fixBill);
                        breakdown.setBacknews("�豸ά�޳ɹ���");
                    }

                } else {
                    backnews="���豸�����ڣ�";
                    breakdown.setBacknews(backnews);
                }
            }
            con.close();
            RequestDispatcher dispatcher=request.getRequestDispatcher("breakdown.jsp");
            dispatcher.forward(request,response);
        }
        catch (SQLException exp) {
            backnews="�������ݿ�ʧ�ܣ������ԣ�";
            RequestDispatcher dispatcher=request.getRequestDispatcher("breakdown.jsp");
            dispatcher.forward(request,response);
        }

    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,IOException {
        doPost(request,response);
    }
}


