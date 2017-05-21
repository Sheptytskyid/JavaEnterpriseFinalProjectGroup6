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

    <title>Add a project</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>

<body>

<div class="container">

    <form:form method="POST" modelAttribute="dtoProject" class="form-signin">
        <h2 class="form-signin-heading">Add a project</h2>
        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="name" class="form-control" placeholder="Project name"
                            autofocus="true"></form:input>
                <form:errors path="name"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="description">
            <div class="form-group">
                <form:textarea type="text" path="description" class="form-control" placeholder="Description"></form:textarea>
            </div>
        </spring:bind>

        <spring:bind path="goal">
            <div class="form-group">
                <form:input type="text" path="goal" class="form-control" placeholder="Goal"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="category">
            <div class="form-group">
                <form:input type="text" path="category" class="form-control" placeholder="Category"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="cost">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="cost" class="form-control"
                            placeholder="Total cost"></form:input>
                <form:errors path="cost"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="minInvestment">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="minInvestment" class="form-control"
                            placeholder="Minimum investment"></form:input>
                <form:errors path="minInvestment"></form:errors>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>