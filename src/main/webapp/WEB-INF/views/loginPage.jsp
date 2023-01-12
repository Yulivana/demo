<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>My Custom Login Page</title>
</head>
<body style='margin:50px;'>
<h2>My Custom Login Page</h2>
<form action="${pageContext.request.contextPath}/loginS" method="post">
    <c:if test="${error != null}">
        <p style='color:red'>
            Invalid username and password.
        </p>
    </c:if>
    <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username"/>
    </p>
    <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>
    </p>

    <button type="submit">Log in</button>
</form>
</body>
</html>