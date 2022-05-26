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
public class routeshow extends HttpServlet {
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
            query ="select * from route";
            pst = con.prepareStatement(query);
            rs=pst.executeQuery();
            out.println("<center><h1>ROUTE TABLE</h1>");
            out.println("<table border=1>"); 
            out.println("<tr><th>Route_ID</th>");
            out.println("<th>Duration</th>"); 
            out.println("<th>Airline_ID</th>");
            out.println("<th>Departure_Location</th>");
            out.println("<th>Arrival_Location</th>");
            out.println("<th>Bussiness_Fare</th>");
            out.println("<th>Eco_Fare</th>");
            out.println("<th>City_ID</th>");
            out.println("</tr>");
            while (rs.next()) {
            out.println("<tr>");
            out.println("<td>"+rs.getString(1) +"</td>"); 
            out.println("<td>"+rs.getFloat(2) +"</td>");
            out.println("<td>"+rs.getString(3) +"</td>");
            out.println("<td>"+rs.getString(4) +"</td>");
            out.println("<td>"+rs.getString(5) +"</td>");
            out.println("<td>"+rs.getFloat(6) +"</td>");
            out.println("<td>"+rs.getFloat(7) +"</td>");
            out.println("<td>"+rs.getString(8) +"</td>");
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
            java.util.logging.Logger.getLogger(routeshow.class.getName()).log(Level.SEVERE,null,e);
        } catch(ClassNotFoundException e){  
            java.util.logging.Logger.getLogger(routeshow.class.getName()).log(Level.SEVERE,null,e);
        }
    }
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        processRequest(request, response);
        }catch(SQLException e){
            java.util.logging.Logger.getLogger(routeshow.class.getName()).log(Level.SEVERE,null,e);
        } catch(ClassNotFoundException e){  
            java.util.logging.Logger.getLogger(routeshow.class.getName()).log(Level.SEVERE,null,e);
        }
   }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
