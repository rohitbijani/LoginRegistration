<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Registration</title>
</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
if(request.getSession().getAttribute("username")==null)
{
	response.sendRedirect("login.jsp");
}
/* String name=request.getParameter("uname"); */
else{

String s= (String) request.getSession().getAttribute("username");
out.print("<h3>Welcome "+s+"! </h3>");
/* session.setAttribute("username", name); */


out.print("<a href='logout'>Logout</a>");
}
%>
</body>
</html>