<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<c:url value="/resources/css/test.css" />">
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

<title>User Registration</title>
</head>

<body>
	<div id="testheader">
		<div class="container">
			<div class=row>
				<div class="col-md-5 ">
					<a href="./home"><h3>Student Registration</h3></a>
				</div>
				<%@ include file="header.jsp"%>
				<div class="col-md-2">
					<a class="btn btn-danger text-dark" href="../logout">Log out</a>
				</div>
			</div>
		</div>

	</div>
	<!-- <div id="testsidebar">Hello World </div> -->
	<div class="container">
		<div class="sidenav">

			 <button class="dropdown-btn" > Class Management <i class="fa fa-caret-down"></i></button>
        
           <div class="dropdown-container">
          <a href="./course/register">Course Registration </a>
          <a href="./student/register">Student Registration </a>
          <a href="./student/lists">Student Search </a>
        </div>
        <a href="./lists">Users Management</a>
		</div>
		<div class="main_contents">
			<div id="sub_content">
				<form class="row g-3 mt-3 ms-2">
					<div class="col-auto">
					<a href="./register">
						<button type="button" class="btn btn-secondary">Add User</button>
					
					</a>
					</div>
				</form>

				<table class="table table-striped" id="stduentTable">
					<thead>
						<tr>

							<th scope="col">User ID</th>
							<th scope="col">User Name</th>
							<th scope="col">Details</th>

						</tr>
					</thead>
					<tbody>
					<c:forEach var="admin" items="${admins }">
					<tr>
						<td>${admin.id }</td>
						<td>${admin.name }</td>
						<td>
						<a href="./update/${admin.id }">
								<button type="button" class="btn btn-success">Update</button>
						</a>
							</td>
							<td>
								<a href="./delete/${admin.id }">
								<button type="submit" class="btn btn-secondary mb-3">Delete</button>	
								</a>
							</td>
					
					</tr>
						
					</c:forEach>
						
					</tbody>
				</table>

				<div class="modal fade" id="exampleModal" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Student
									Deletion</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">

								<h5 style="color: rgb(127, 209, 131);">Are you sure want to
									delete !</h5>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-success col-md-2"
									data-bs-dismiss="modal">Ok</button>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="testfooter">
		<span>Copyright &#169; ACE Inspiration 2022</span>
	</div>
	<script>
		/* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
		var dropdown = document.getElementsByClassName("dropdown-btn");
		var i;

		for (i = 0; i < dropdown.length; i++) {
			dropdown[i].addEventListener("click", function() {
				this.classList.toggle("active");
				var dropdownContent = this.nextElementSibling;
				if (dropdownContent.style.display === "block") {
					dropdownContent.style.display = "none";
				} else {
					dropdownContent.style.display = "block";
				}
			});
		}
	</script>
</body>

</html>






<body>

</body>

