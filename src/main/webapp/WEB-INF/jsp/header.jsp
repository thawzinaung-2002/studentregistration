<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 		<div class="col-md-5">
            <p>User: <c:out value="${loginObj.name }"/></p>
            <jsp:useBean id="date" class="java.util.Date" />
            <p>Current Date : <fmt:formatDate value="${date }" pattern="yyyy-MM-dd"/> </p>
        </div>  
</body>
</html>