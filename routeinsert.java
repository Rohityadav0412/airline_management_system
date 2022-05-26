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
public class routeinsert extends HttpServlet {
       protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException,SQLException {
        response.setContentType("text/html;charset=UTF-8");
        Connection con;
        PreparedStatement pst;
        String query,airline_id,depart_loc,arrival_loc,city_id,route_id;
        int count;
        int duration,eco_fare,bussiness_fare;
        try (PrintWriter out = response.getWriter()) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","rohit");
            query ="insert into route values(?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(query);
            route_id= request.getParameter("rroute_id");
            duration = Integer.parseInt(request.getParameter("rduration"));
            airline_id = request.getParameter("rairline_id");
            depart_loc = request.getParameter("rdepart_locn");
            arrival_loc = request.getParameter("rarrival_locn");
            eco_fare = Integer.parseInt(request.getParameter("reco_fare"));
            bussiness_fare = Integer.parseInt(request.getParameter("rbussiness_fare"));
            city_id=request.getParameter("rcity_id");
            pst.setString(1,route_id);
            pst.setInt(2, duration);
            pst.setString(3,airline_id);
            pst.setString(4,depart_loc);
            pst.setString(5,arrival_loc);
            pst.setInt(7, eco_fare);
            pst.setInt(6, bussiness_fare);
            pst.setString(8, city_id);
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
