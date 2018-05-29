package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginFilter implements Filter {
	
public void init(FilterConfig arg0) throws ServletException {}
	
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String uname=request.getParameter("uname");
		String password=request.getParameter("pwd");
//		System.out.println(uname);
		if(uname!="" || password!="") {
			chain.doFilter(request, response);
		}
		else {
			out.print("Username and Password can't be blank");
			RequestDispatcher dispatcher=request.getRequestDispatcher("/login.jsp");
			dispatcher.include(request, response);
		}
		
	}
	
	public void destroy() {}

}
