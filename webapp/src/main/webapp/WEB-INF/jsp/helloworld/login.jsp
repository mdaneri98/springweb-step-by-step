<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Login page</title>
</head>
<body>

    <c:url var="loginUrl" value="/login" />
    <form method="post" action="${loginUrl}">
        <div>
            <label><spring:message code="hwc.login.username"/><input type="text" name="username"/></label>
        </div>
        <div>
            <label><spring:message code="hwc.login.password"/><input type="password" name="password"/></label>
        </div>
        <div>
            <label><spring:message code="hwc.login.remember_me"/><input type="checkbox" name="remember_me"/></label>
        </div>
        <div>
            <label><spring:message code="hwc.login.submit"/><input type="submit"/></label>
        </div>
    </form>

</body>
</html>
