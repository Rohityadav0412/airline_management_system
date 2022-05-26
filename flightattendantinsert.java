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
public class flightattendantinsert extends HttpServlet {
       protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException,SQLException {
        response.setContentType("text/html;charset=UTF-8");
        Connection con;
        PreparedStatement pst;
        String query,atten_id,atten_name,atten_gender,name_duty,airplane_id;
        int count;
        float age;
        try (PrintWriter out = response.getWriter()) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","rohit");
            query ="insert into FLIGHT_ATTENDANT values(?,?,?,?,?,?)";
            pst = con.prepareStatement(query);
            atten_id= request.getParameter("faatten_id");
            atten_name= request.getParameter("faatten_name");
            age = Float.parseFloat(request.getParameter("faatten_age"));
            atten_gender= request.getParameter("faatten_gender");
            name_duty= request.getParameter("faname_duty");
            airplane_id= request.getParameter("faairplane_id");
            pst.setString(1,atten_id);
            pst.setString(2,atten_name);
            pst.setFloat(3, age);
            pst.setString(4,atten_gender);
            pst.setString(5,name_duty);
            pst.setString(6, airplane_id);
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
