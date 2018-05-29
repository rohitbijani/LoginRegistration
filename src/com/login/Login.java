package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con=null;
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rohit","root","root");
			PreparedStatement statement=con.prepareStatement("select * from users where username=? AND password=?");
			String uname=request.getParameter("uname");
			String password=request.getParameter("pwd");
			statement.setString(1, uname);
			statement.setString(2, password);
			
			boolean flag=false;
			ResultSet rs=statement.executeQuery();
			flag=rs.next();
			
			if(flag){
				HttpSession session=request.getSession();
				session.setAttribute("username", uname);
				response.sendRedirect("welcome.jsp");
//				RequestDispatcher dispatcher=request.getRequestDispatcher("/welcome.jsp");
//				dispatcher.forward(request, response);
			}
			else{
				out.print("Username or Password incorrect!");
				RequestDispatcher dispatcher=request.getRequestDispatcher("/login.jsp");
				dispatcher.include(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Close connection error");
			}
		}
	}
	
}
