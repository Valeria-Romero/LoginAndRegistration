<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login and registration</title>
		<link rel="stylesheet" href="/css/index.css"/>
	</head>
	<body>
		<h1>
			Welcome back 
			<c:out value="${firstName}"></c:out>
		</h1>
		<form action="/logout" method="GET" >
			<button type="submit">
				Logout
			</button>
		</form>
	</body>
</html>