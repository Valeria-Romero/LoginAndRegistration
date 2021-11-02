<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login and registration</title>
		<link rel="stylesheet" href="/css/index.css"/>
	</head>
	<body>
		<form method="POST" action="/registerUser">
			<h1>Register a new user</h1>	
			<div>
				<label for="email"> Email: </label>
				<input type="text" id="email" name="email" />
			</div>
			<div>
				<label for="firstname"> First name: </label>
				<input type="text" id="firstname" name="firstname" />
			</div>
			<div>
				<label for="lastname"> Last name: </label>
				<input type="text" id="lastname" name="lastname" />
			</div>
			<div>
				<label for="password"> Password: </label>
				<input type="password" id="password" name="password" />
			</div>
			<div>
				<label for="passwordConfirmation"> Password confirmation: </label>
				<input type="password" id="passwordConfirmation" name="passwordConfirmation" />
			</div>
			<div>
				<button type="submit">
					Register
				</button>
			</div>
			<div class="errorMessage">
				<c:out value="${errorMessage}"></c:out>
			</div>
		</form>
		<form method="POST" action="/validateUser">
			<h1>Login</h1>
			<div>
				<label for="userEmail"> Email: </label>
				<input type="text" id="userEmail" name="userEmail" />
			</div>
			<div>
				<label for="userPassword"> Password: </label>
				<input type="password" id="userPassword" name="userPassword" />
			</div>
			<div>
				<button type="submit">
					Login
				</button>
			</div>
			<div>
				<c:out value="${loginErrorMessage}"></c:out>
			</div>
		</form>
	</body>
</html>