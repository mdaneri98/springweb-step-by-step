<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<body>
<h2>Hello <c:out value="${username}"/>!</h2>

<h5>Your user id is <c:out value="${userId}"/></h5>

<h5>Your actually logged user id is: <c:out value="${loggedUser.id}", <c:out value="${loggedUser.username}" /> /> </h5>

</body>
</html>