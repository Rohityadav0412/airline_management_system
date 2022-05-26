import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
public class query8 extends HttpServlet {
       protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException,SQLException {
        response.setContentType("text/html;charset=UTF-8");
        Connection con;
        PreparedStatement pst;
           ResultSet rs;
        String query,acctype;
        int count;
        float balance;
        try (PrintWriter out = response.getWriter()) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","rohit");
            query ="select name,u_id from receptionist where u_id in (select rec_uid from booking where airplane_id in (select airline_id from route where duration=4.00))";
            pst = con.prepareStatement(query);
            rs=pst.executeQuery();
            out.println("<center><h1>NAME AND U_ID OF RECEPTIONIST WHO BOOKED TICKETS FOR FLIGHTS WITH DURATION EQUAL TO 4 HOURS</h1>");
            out.println("<table border=1>"); 
            out.println("<tr><th>NAME</th>");
            out.println("<th>U_ID</th>");
            out.println("</tr>");
            while (rs.next()) {
            out.println("<tr>");
            out.println("<td>"+rs.getString(1) +"</td>"); 
            out.println("<td>"+rs.getString(2) +"</td>");
            out.println("</tr>");
                }
            out.println("</table>");
            out.println("</center>");
       }
    }
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        processRequest(request, response);
        }catch(SQLException e){
            java.util.logging.Logger.getLogger(query8.class.getName()).log(Level.SEVERE,null,e);
        } catch(ClassNotFoundException e){  
            java.util.logging.Logger.getLogger(query8.class.getName()).log(Level.SEVERE,null,e);
        }
    }
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        processRequest(request, response);
        }catch(SQLException e){
            java.util.logging.Logger.getLogger(query8.class.getName()).log(Level.SEVERE,null,e);
        } catch(ClassNotFoundException e){  
            java.util.logging.Logger.getLogger(query8.class.getName()).log(Level.SEVERE,null,e);
        }
   }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}



