package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con=null;
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rohit","root","root");
			PreparedStatement statement=con.prepareStatement("insert into users values(?,?,?)");
			String uname=request.getParameter("uname");
			String email=request.getParameter("email");
			String password=request.getParameter("pwd");
			statement.setString(1, uname);
			statement.setString(2, password);
			statement.setString(3, email);
			int i=statement.executeUpdate();
			
			if (i>0) {
				out.print("Registration successful!");
				RequestDispatcher dispatcher=request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
//				response.sendRedirect("login.jsp");				
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
