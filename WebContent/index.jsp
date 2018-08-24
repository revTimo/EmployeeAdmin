<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<f:view>
<p>login page</p>
<form method="post" action="AdminController">
Admin_name:<input type="text" name="name">
Password:<input type="password" name="password">
<input type="hidden" name="action" value="login">
<input type="submit" value="Login">
</form>
<a href="register.jsp">create admin</a>
</f:view>
</body>
</html>