import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

@WebServlet(value="/junk")
public class JunkServlet extends HttpServlet {
     @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
         try {          
           Connection connection = Conn.getConnection();
           Statement stmt = connection.createStatement();
           stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
           stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
           stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
           ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
           while (rs.next()) {
               response.getWriter().println("Read from DB: " + rs.getTimestamp("tick") + "<br>");
           }
         } catch (Exception e) {}
        response.getWriter().println("Junk Hello world!!");
    }
}
