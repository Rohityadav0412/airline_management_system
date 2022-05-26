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
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
public class func extends HttpServlet {
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

            CallableStatement cstmt=con.prepareCall("begin ? :=func_cal5; end;");
            cstmt.registerOutParameter(1,Types.INTEGER);
            cstmt.execute();
            int result=cstmt.getInt(1);
            out.println("<center><h1>Average of Ticket Bussiness Fares Using Function</h1>");
            out.println("<table border=1>"); 
            out.println("<tr><th>Average</th>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>"+result +"</td>"); 
            out.println("</tr>");
            cstmt.close();
            con.close();
            
            
       }
    }
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        processRequest(request, response);
        }catch(SQLException e){
            java.util.logging.Logger.getLogger(func.class.getName()).log(Level.SEVERE,null,e);
        } catch(ClassNotFoundException e){  
            java.util.logging.Logger.getLogger(func.class.getName()).log(Level.SEVERE,null,e);
        }
    }
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        processRequest(request, response);
        }catch(SQLException e){
            java.util.logging.Logger.getLogger(func.class.getName()).log(Level.SEVERE,null,e);
        } catch(ClassNotFoundException e){  
            java.util.logging.Logger.getLogger(func.class.getName()).log(Level.SEVERE,null,e);
        }
   }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
