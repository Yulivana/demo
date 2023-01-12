<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your users</title>
</head>
<body>
    <h2> Your users: </h2>
    <table>
	<c:forEach var="num" items="${users}">
	    <tr>
            <td>${num}</td>
        </tr>
    </c:forEach>
    </table>
    <button onclick="location.href='${pageContext.request.contextPath}/logout'"> logout </button>
</body>
</html>