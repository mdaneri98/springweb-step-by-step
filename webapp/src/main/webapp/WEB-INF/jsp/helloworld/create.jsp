<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: matiasdaneri
  Date: 31/08/2024
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create form</title>
</head>
<body>

<c:url var="posturl" value="/create" />
<form:form modelAttribute="userForm" action="${posturl}" method="post">
    <div>
        <label> <spring:message code="hwc.create.username"/> <form:input path="username" type="text" /></label>
        <form:errors path="username" element="p" cssStyle="color:red;"/>
    </div>
    <div>
        <label> <spring:message code="hwc.create.password"/> <form:input path="password" type="text" /></label>
        <form:errors path="password" element="p" cssStyle="color:red;"/>
    </div>
    <div>
        <label> <spring:message code="hwc.create.repeatPassword"/> <form:input path="repeatPassword" type="password" placeholder="Repeat password"/></label>
        <form:errors path="repeatPassword" cssStyle="color:error;" element="p"/>
    </div>
    <div>
        <input type="submit">
    </div>
</form:form>

</body>
</html>
