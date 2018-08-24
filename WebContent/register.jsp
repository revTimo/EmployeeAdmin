<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<f:view>
<form method="post" action="AdminController">
Name:<input type="text" name="name">
Email:<input type="email" name="eamil">
Password:<input type="password" name="password">
<input type="hidden" name="action" value="register">
<input type="submit" value="register">
</form>
</f:view>
</body>
</html>