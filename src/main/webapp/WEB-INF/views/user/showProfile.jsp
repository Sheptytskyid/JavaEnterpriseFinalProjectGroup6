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

    <title>User profile</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/theme.css" rel="stylesheet">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>

<body>

<div class="container">
    <h2>User Profile | <a href="${contextPath}/user/${dtoUserProfile.id}/edit">Edit</a></h2>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Property</th>
                <th>Value</th>
            </tr>
        </thead>

        <tr>
            <td>Name</td>
            <td><c:out value="${dtoUserProfile.name}"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><c:out value="${dtoUserProfile.email}"/></td>
        </tr>
        <tr>
            <td>Address</td>
            <td><c:out value="${dtoUserProfile.address}"/></td>
        </tr>
        <tr>
            <td>Phone</td>
            <td><c:out value="${dtoUserProfile.phoneNumber}"/></td>
        </tr>
    </table>



</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>