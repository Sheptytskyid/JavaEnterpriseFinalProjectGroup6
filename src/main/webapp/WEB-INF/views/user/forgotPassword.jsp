<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Reset Password</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>

<body>

<div class="container">
<c:if test="${message!=''}">
    <div align="center"><h3>${message}</h3></div>
</c:if>
    <form:form method="POST" modelAttribute="user" class="form-signin">
        <h2 class="form-signin-heading">Enter your Email</h2>
        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="email" class="form-control" placeholder="Email"
                            autofocus="true"></form:input>
                <form:errors path="email"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="password">
            <form:input type="hidden" path="password" value="default"></form:input>
        </spring:bind>
        <spring:bind path="confirmPassword">
            <form:input type="hidden" path="confirmPassword" value="default"></form:input>
        </spring:bind>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

    <h4 class="text-center"><a href="${contextPath}/user/login">Log In</a></h4>
    <h4 class="text-center"><a href="${contextPath}/user/register">Create an account</a></h4>
</div>

</form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>