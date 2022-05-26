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
public class pilotinsert extends HttpServlet {
       protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException,SQLException {
        response.setContentType("text/html;charset=UTF-8");
        Connection con;
        PreparedStatement pst;
        String query,pi_id,airline_allocated,pi_name,pi_gender,date_of_qualification,sc_name;
        int count;
        int age;
        try (PrintWriter out = response.getWriter()) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","rohit");
            query ="insert into pilot values(?,?,?,?,?,?,?)";
            pst = con.prepareStatement(query);
            pi_id= request.getParameter("pipi_id");
            airline_allocated = request.getParameter("piairline_allocated");
            pi_name= request.getParameter("pipi_name");
            pi_gender= request.getParameter("pipi_gender");
            date_of_qualification= request.getParameter("pidate_of_qualification");
            age = Integer.parseInt(request.getParameter("pipi_age"));
            sc_name=request.getParameter("pisc_name");
            pst.setString(1,pi_id);
            pst.setString(5,airline_allocated);
            pst.setString(2, pi_name);
            pst.setString(4, pi_gender);
            pst.setString(6, date_of_qualification);
            pst.setInt(3, age);
            pst.setString(7,sc_name);
              count = pst.executeUpdate();
            out.print(count+" Record(s) inserted");
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
