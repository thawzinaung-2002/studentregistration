<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>

<title>Admin Registration</title>
<style>
p
{
	color: red;
	padding: 0;
	margin-top: 0;
	font-weight: bold;
	text-align: left;!important;
}
.error 
{
	color: red;
	
}
</style>
</head>

<body class="bg-success bg-gradient">

	<div class="container-fluid">
		<div class="col-12">
			<form:form action="doregister" modelAttribute="admin" method="post">
				<h2 class="col-md-6 offset-md-2 mb-5 mt-4">User Registration</h2>
				<div class="col-12">
					<c:if test="${msg!=null }">
            		<p class="text-center">${msg}</p>
            	</c:if>
				</div>
				
				<div class="row mb-4">
					<div class="col-md-2"></div>
					<form:label for="name" class="col-md-2 col-form-label" path="name">Name</form:label>
					<div class="col-md-4">
						<form:input type="text" class="form-control" id="name" path="name" />
					</div>
					<div class="col-md-4">
						<form:errors path="name" cssClass="error" />
					</div>
				</div>
				<div class="row mb-4">
					<div class="col-md-2"></div>
					<form:label for="email" class="col-md-2 col-form-label" path="email">Email</form:label>
					<div class="col-md-4">
						<form:input type="email" class="form-control" id="email" path="email"/>
					</div>
					<div class="col-md-4">
						<form:errors path="email" cssClass="error" />
					</div>
					
				</div>
				<div class="row mb-4">
					<div class="col-md-2"></div>
					<form:label for="password" class="col-md-2 col-form-label" path="password">Password</form:label>
					<div class="col-md-4">
						<form:input type="password" class="form-control" id="password" path="password"/>
					</div>
					<div class="col-md-4">
						<form:errors path="password" cssClass="error" />
					</div>
					
				</div>
				<div class="row mb-4">
					<div class="col-md-2"></div>
					<form:label for="confirmPassword" class="col-md-2 col-form-label" path="confirmPassword">Confirm
						Password</form:label>
					<div class="col-md-4">
						<form:input type="password" class="form-control" id="confirmPassword" path="confirmPassword" />
					</div>
					<div class="col-md-4">
						<form:errors path="confirmPassword" cssClass="error" />
					</div>
					
				</div>
				<div class="row mb-4">
					<div class="col-md-2"></div>
					<form:label for="userRole" class="col-md-2 col-form-label" path="role">User
						Role</form:label>
					<div class="col-md-4">
						<form:select class="form-select" aria-label="Education" id="userRole" path="role">
							<option value="Admin" selected>Admin</option>
							<option value="User">User</option>
						</form:select>
					</div>
				</div>
				<div class="row mb-4">
					<div class="col-md-4"></div>
					<div class="col-md-8">
						<button type="submit" class="btn btn-info text-dark col-md-2">Add</button>
					</div>
				</div>
			</form:form>
			<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-8">
						<p class="message ">Already registered? <a href="../" class="text-warning">Login</a></p>
					</div>
				</div>
		</div>
	</div>
	
	<div id="testfooter" style="text-align:center;padding:1em;color:black;background: green;">
		<span>Copyright &#169; ACE Inspiration 2022</span>
	</div>

</body>

</html>


