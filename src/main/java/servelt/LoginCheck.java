package servelt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginCheck", urlPatterns = "/LoginCheck")
public class LoginCheck extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:64101;database=MyDB",
					"sa","123");
			PreparedStatement ps = con.prepareStatement("select * from users where username = ? and password = ?");
			ps.setString(1, user);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			PrintWriter out = resp.getWriter();
			
			if(rs.next()) 
				out.println("<script>"
							+ "alert('Welcome User "+user+"');" 
							+ "window.location='home.jsp';"
							+ "</script>");		
			else
				out.println("<script>"
							+ "alert('incorrect username or pass');" 
							+ "window.location='home.jsp';"
							+ "</script>");	
			
		}catch (Exception e) {
			// TODO: handle exception
			PrintWriter out = resp.getWriter();
			out.println(e);
		}
	}
}
