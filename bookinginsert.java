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
public class bookinginsert extends HttpServlet {
       protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException,SQLException {
        response.setContentType("text/html;charset=UTF-8");
        Connection con;
        PreparedStatement pst;
        String query,iata_code,date,status,uid,airplane_id,passenger_id;
        int count;
        float tot_fare;
        try (PrintWriter out = response.getWriter()) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","rohit");
            query ="insert into BOOKING values(?,?,?,?,?,?,?)";
            pst = con.prepareStatement(query);
            iata_code= request.getParameter("biata_code");
            tot_fare = Float.parseFloat(request.getParameter("btot_fare"));
            date = request.getParameter("bdate");
            status = request.getParameter("bstatus");
            uid = request.getParameter("buid");
            airplane_id = request.getParameter("bairplane_id");
            passenger_id = request.getParameter("bpassenger_id");
            

            pst.setString(1,iata_code);
            pst.setFloat(3, tot_fare);
            pst.setString(2,date);
            pst.setString(4,status);
            pst.setString(7,uid);
            pst.setString(6,airplane_id);
            pst.setString(5, passenger_id);
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
