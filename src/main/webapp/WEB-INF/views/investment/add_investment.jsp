<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
	<title>Add Investment</title>
	<link rel="shortcut icon" href="${contextPath}/resources/img/favicon.png" type="image/x-icon">
	<link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
	<link href="${contextPath}/resources/css/font-awesome.css" rel="stylesheet">
	<link href="${contextPath}/resources/css/main.css" rel="stylesheet">
	<link href="${contextPath}/resources/css/style.css" rel="stylesheet">

	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>
<form method="post" action="/project/${project.id}/investment/add">
	<div class="container">
		<c:if test="${message != null}">

			<div class="alert alert-danger alert-dismissable">
				<button type="button" class="close" data-dismiss="alert">
					<span class="fa fa-close fa-fw"></span>
				</button>
					<%--<strong>You can go mad... </strong><a href="#" class="alert-link">Go wild!</a>--%>
					${message}
			</div>
		</c:if>
	</div>
	<div class="container">
		<h2 class="form-signin-heading">Add an investment</h2>
		<p>Here you can add your investment in ${project.desc.name}</p>
		<p>Total investment needed: ${project.desc.cost-investedSum}</p>
		<p>Minimal investment: ${project.desc.minInvestment}</p>
		<input type="number"
			   name="sum"
			   placeholder="Your investment"
			   min="${project.desc.minInvestment}"
			   max="${project.desc.cost-investedSum}"
			   step="${project.desc.minInvestment}"/><br>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<button class="btn btn-lg btn-primary" type="submit">Submit</button>
	</div>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
