<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js "></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js "></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js "></script>
</head>
<body style="text-align: center;">

	<h3 style="color: red">User Registration</h3>
	<form:form modelAttribute="user" action="users" method="POST">
		<table style="width: 50%" align="center" class=table>
			<tr>
				<td>Username</td>
				<td><form:input path="username" /></td>
				<td><form:errors path="username" style="color:red;"
						cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><form:password path="password" /></td>
				<td><form:errors path="password" style="color:red;"
						cssClass="error"></form:errors>
				<td>
			</tr>
			<tr>
				<td>Email</td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" style="color:red;"
						cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td>ContactNo</td>
				<td><form:input path="contactNo" /></td>
				<td><form:errors path="contactNo" style="color:red;"
						cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><form:input path="age" /></td>
				<td><form:errors path="age" style="color:red;" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td>Role</td>
				<td><form:select path="userRole">
						<form:option value="Admin" />
						<form:option value="User" />
					</form:select></td>
			</tr>
		</table>
		<tr>
			<td><input type="Submit" value="Submit" class="button"/>&nbsp;&nbsp;&nbsp; <input
				type="Reset" value="Reset" class="button"/></td>
		</tr>
	</form:form>
	<c:if test="${not empty register}">
			<div class="alert">
				<p>${register}</p>
			</div>
	</c:if>

</body>
</html>