<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/test.css" />">
<title> Student Registration LGN001 </title>
<style>
p
{
	color: red;
	padding: 0;
	margin-top: 0;
	font-weight: bold;
	text-align: left;!important;
}
</style>
</head>
<body class="login-page-body"> 
  
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>
            <c:if test="${msg!=null }">
            	<p>${msg}</p>
            </c:if>
          </div>
        </div>
        <form:form class="login-form" action="dologin" method="post" name="confirm" modelAttribute="user">
          <form:input type="text" placeholder="User ID" path="email"/>
          <p class="text-left">
          	<form:errors path="email" cssClass="error"/>
          </p>
          <form:input type="password" placeholder="Password" path="password"/>
          <p class="text-left">
          	<form:errors path="password" cssClass="error text-center"/>
          </p>
       
          <button>login</button>
          <p class="message">Not registered? <a href="./admin/register">Create an account</a></p>
        </form:form>
      </div>
    </div>
</body>

</html>