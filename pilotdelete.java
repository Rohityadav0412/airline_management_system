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
public class pilotdelete extends HttpServlet {
       protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException,SQLException {
        response.setContentType("text/html;charset=UTF-8");
        Connection con;
        PreparedStatement pst;
        String query,pi_id;
        int count;
        try (PrintWriter out = response.getWriter()) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","rohit");
            query ="delete from pilot where pi_id=?";
            pst = con.prepareStatement(query);
            pi_id= request.getParameter("pipi_id");
            pst.setString(1,pi_id);
              count = pst.executeUpdate();
            out.print(count+" Record(s) deleted");
       }
    }
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        processRequest(request, response);
        }catch(SQLException e){
            
        } catch(ClassNotFoundException e){     
        }
    }
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        processRequest(request, response);
        }catch(SQLException e){
            
        } catch(ClassNotFoundException e){   
        }
   }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
