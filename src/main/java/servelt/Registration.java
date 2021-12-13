package servelt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Registration", urlPatterns = "/Registration")
public class Registration extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = req.getParameter("user");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			Connection con = 
					DriverManager.getConnection("jdbc:sqlserver://localhost:64101;databaseName=MyDB","sa","123");
			
			PreparedStatement ps = con.prepareStatement("insert into users values(?,?,?)");
			ps.setString(1, uname);
			ps.setString(2, email);
			ps.setString(3, pass);
			ps.executeUpdate();
			resp.sendRedirect("home.jsp");
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
