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
public class passengerinsert extends HttpServlet {
       protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException,SQLException {
        response.setContentType("text/html;charset=UTF-8");
        Connection con;
        PreparedStatement pst;
        String query,passenger_id,name,seat_number,type,gender,uid,airplane_id,iata_code;
        int count;
        int age;
        try (PrintWriter out = response.getWriter()) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","rohit");
            query ="insert into passenger values(?,?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(query);
            passenger_id= request.getParameter("ppassenger_id");
            name = request.getParameter("pname");
            seat_number = request.getParameter("pseat_number");
            type = request.getParameter("ptype");
            gender = request.getParameter("pgender");
            age = Integer.parseInt(request.getParameter("page"));
            uid = request.getParameter("puid");
            airplane_id = request.getParameter("pairplane_id");
            iata_code = request.getParameter("piata_code");

            pst.setString(1,passenger_id);
            pst.setString(3,name);
            pst.setString(6,seat_number);
            pst.setString(2,type);
            pst.setString(4,gender);
            pst.setInt(5, age);
            pst.setString(9,uid);
            pst.setString(8,airplane_id);
            pst.setString(7,iata_code);
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
