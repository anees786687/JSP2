package servelt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BookData", urlPatterns = "/BookData")
public class BookData extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = req.getParameter("bookname");
		String author = req.getParameter("author");
		float price = Float.parseFloat(req.getParameter("price"));
		String link = req.getParameter("link");
		
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:64101;database=MyDB",
					"sa","123");
			PreparedStatement ps = con.prepareStatement("insert into books values(?,?,?,?)");
			
			ps.setString(1, name);
			ps.setString(2, author);
			ps.setFloat(3, price);
			ps.setString(4, link);
			ps.executeUpdate();
			resp.sendRedirect("home.jsp"); // use this to redirect the site to the requried web page 
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		
	}
}
