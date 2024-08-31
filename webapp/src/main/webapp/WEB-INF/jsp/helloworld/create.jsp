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
<form action="${posturl}" method="post">
    <div>
        <label>
            Username
            <input name="username" type="text">
        </label>
    </div>
    <div>
        <input type="submit">
    </div>
</form>

</body>
</html>
