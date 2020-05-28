

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


public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("type/html");
		String id = request.getParameter("bookid");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin://localhost:1521:xe", "system", "ali");
			PreparedStatement stat = con.prepareStatement("select * from book where book_id=?");
			stat.setString(1, id);
			boolean flag = stat.execute();
			String html="<html>";
            html+="<head><title>Book</title></head>";
            html+="<body>";
            
			if(flag)
			{
				ResultSet rs = stat.executeQuery();
				html+="<div>";
	            html+="<table>";
				html+="<tr>";
	            html+="<th>Title</th>";
	            html+="<th>ID</th>";
	            html+="<th>Author</th>";
	            html+="<th>Publisher</th>";
	            html+="</tr>";
				while(rs.next())
				{
					String bookid = rs.getString("ID");
					String title = rs.getString("TITLE");
					String author = rs.getString("AUTHOR");
					String publisher = rs.getString("PUBLISHER");
					html+="<tr>";
	                html+="<td>"+title+"</td>";
	                html+="<td>"+bookid+"</td>";
	                html+="<td>"+author+"</td>";
	                html+="<td>"+publisher+"</td>";
	                html+="</tr>";
					
				}
				
				html+="</table></div></body></html>";
				out.println(html);
			}
			else
			{
				out.println("<h1 align=\"center\">No book Found for this ID</h1>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
