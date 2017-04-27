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

    <form:form method="POST" modelAttribute="project" class="form-signin">
        <h2 class="form-signin-heading">Add a project</h2>
        <spring:bind path="desc.name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="desc.name" class="form-control" placeholder="Project name"
                            autofocus="true"></form:input>
                <form:errors path="desc.name"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="desc.description">
            <div class="form-group">
                <form:textarea type="text" path="desc.description" class="form-control" placeholder="Description"></form:textarea>
            </div>
        </spring:bind>

        <spring:bind path="desc.goal">
            <div class="form-group">
                <form:input type="text" path="desc.goal" class="form-control" placeholder="Goal"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="category.name">
            <div class="form-group">
                <form:input type="text" path="category.name" class="form-control" placeholder="Category"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="desc.cost">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="desc.cost" class="form-control"
                            placeholder="Total cost"></form:input>
                <form:errors path="desc.cost"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="desc.minInvestment">
            <div class="form-group">
                <form:input type="text" path="desc.minInvestment" class="form-control" placeholder="Minimum investment"></form:input>
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