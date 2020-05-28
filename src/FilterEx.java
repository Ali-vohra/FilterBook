

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

public class FilterEx implements Filter {

  
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String username = request.getParameter("uname");
		String upass = request.getParameter("upass");
		if(!(username.equalsIgnoreCase("abc") && upass.equalsIgnoreCase("123456")))
		{
			RequestDispatcher rs = request.getRequestDispatcher("index.html");
			rs.include(request, response);
			out.println("<h3 align=\"center\">Invalid Username or Password</h3>");
		}
		else
		{
			chain.doFilter(request, response);
		}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
