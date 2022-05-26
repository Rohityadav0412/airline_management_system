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
public class airplaneinsert extends HttpServlet {
       protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException,SQLException {
        response.setContentType("text/html;charset=UTF-8");
        Connection con;
        PreparedStatement pst;
        String query,id, name,from_loc,dept_date,airline_no,to_loc,city_id,lic_id,route_id;
        int count;
        int tot_dist;
        try (PrintWriter out = response.getWriter()) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","rohit");
            query ="insert into AIRPLANE values(?,?,?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(query);
            id= request.getParameter("aairplane_id");
            name = request.getParameter("aairline_name");
            from_loc = request.getParameter("afrom_location");
            dept_date = request.getParameter("adeparture_date");
            tot_dist = Integer.parseInt(request.getParameter("atotal_distance"));
            airline_no = request.getParameter("aairline_no");
            to_loc = request.getParameter("ato_location");
            route_id=request.getParameter("aroute_id");
            city_id= request.getParameter("acity_id");
            lic_id=request.getParameter("alicience_id");

            pst.setString(1,id);
            pst.setString(2,name);
            pst.setString(3,airline_no);
            pst.setString(4,dept_date);
            pst.setString(5,from_loc);
            pst.setString(6,to_loc);
            pst.setInt(7,tot_dist);
            pst.setString(8,lic_id);
            pst.setString(9,route_id);
            pst.setString(10,city_id);
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
