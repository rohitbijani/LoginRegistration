package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class RegistrationFilter
 */
@WebFilter("/RegistrationFilter")
public class RegistrationFilter implements Filter {
	public void init(FilterConfig arg0) throws ServletException {}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String uname=request.getParameter("uname");
		String password=request.getParameter("pwd");
		String email=request.getParameter("email");
		Pattern pattern= Pattern.compile("^([a-zA-Z0-9])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$");
		System.out.println(uname);
		
		
		if(uname!="" || email!="" ||password!="") {
			chain.doFilter(request, response);
		}
		else {
			
			if (uname==null || uname==""){
				out.println("Name can't be blank");
			}
			else if(!pattern.matcher(email).matches()){
				out.println("Invalid email format");
			}
			else if(password.length()<6){  
				out.println("Password must be at least 6 characters long.");
			}
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("/registration.jsp");
			dispatcher.include(request, response);
		}
		
	}

	public void destroy() {}

}
