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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    
        <title>Update</title>
</head>

<body>
    <div id="testheader">
        <div class="container">
            <div class=row>        
                <div class="col-md-5 ">
            <a href="../../home"><h3>Student Registration</h3></a>
        </div>  
        <%@ include file="header.jsp"%>
				<div class="col-md-2">
					<a class="btn btn-danger text-dark" href="../../../logout">Log out</a>
				</div>       
    </div>
    </div>
    
    </div>
    <!-- <div id="testsidebar">Hello World </div> -->
    <div class="container">
    <div class="sidenav">
        
        <button class="dropdown-btn" > Class Management <i class="fa fa-caret-down"></i></button>
        
           <div class="dropdown-container">
          <a href="../../course/register">Course Registration </a>
          <a href="../register">Student Registration </a>
          <a href="../lists">Student Search </a>
        </div>
        <a href="../../../admin/lists">Users Management</a>
      </div>
      <div class="main_contents">
    <div id="sub_content">
    	<c:if test="${msg!=null }">
    		${msg }
    	</c:if>
        <form:form action="doupdate" modelAttribute="student"
					method="post" enctype="multipart/form-data">
					
					
					<h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student
						Update</h2>
					<form:errors path="*" cssClass="text-danger" />
					<form:hidden path="id"/>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<form:label for="name" class="col-md-2 col-form-label" path="name">Name</form:label>
						<div class="col-md-4">
							<form:input type="text" class="form-control" id="name"
								path="name" />
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<form:label for="dob" class="col-md-2 col-form-label" path="dob">DOB</form:label>
						<div class="col-md-4">
							<form:input type="date" class="form-control" id="dob" path="dob" />
						</div>
					</div>
					<fieldset class="row mb-4">
						<div class="col-md-2"></div>
						<legend class="col-form-label col-md-2 pt-0">Gender</legend>
						<div class="col-md-4">
							<div class="form-check-inline">
								<form:radiobutton path="gender" value="Male" label="Male" />
								<form:radiobutton path="gender" value="Female" label="Female" />
							</div>
						</div>
					</fieldset>

					<div class="row mb-4">
						<div class="col-md-2"></div>
						<form:label for="phone" class="col-md-2 col-form-label"
							path="phone">Phone</form:label>
						<div class="col-md-4">
							<form:input type="text" class="form-control" id="phone"
								path="phone" />
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<form:label for="education" class="col-md-2 col-form-label"
							path="education">Education</form:label>
						<div class="col-md-4">
							<form:select class="form-select" aria-label="Education"
								id="education" path="education" items="${educations }">

							</form:select>
						</div>
					</div>
					<fieldset class="row mb-4">
						<div class="col-md-2"></div>
						<legend class="col-form-label col-md-2 pt-0">
							<form:label path="courses">Attend</form:label>
						</legend>

						<div class="col-md-6">
							<div class="form-check-inline col-md-6 fs-6">
								<form:checkboxes items="${courses }" path="courses" cssClass="mr-5 pr-5"/>
							</div>
						</div>
					</fieldset>

					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="image" class="col-md-2 col-form-label">Photo</label>
						<div class="col-md-4">
							<input type="file" class="form-control" id="image" name="photo">
							<p>
						<c:out value="${emptyErr}" />
						</p>
						</div>
						
					</div>

					<div class="row mb-4">
						<div class="col-md-4"></div>
				
						<div class="col-md-4">
						<input type="submit" class="btn btn-success" value="Update" />
						
						<a href="../lists">
                        <button type="button" class="btn btn-secondary">
                            <span>Cancel</span>
                        </button>
                    </a>
						</div>
					</div>
			</form:form>
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

