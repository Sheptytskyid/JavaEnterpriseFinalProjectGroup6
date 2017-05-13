<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
	<title>Add Investment</title>
</head>
<body>

<form:form method="POST" modelAttribute="investment" class="form-signin">
	<h2 class="form-signin-heading">Add an investment</h2>
	<p>Here you can add you investment in ${project.desc.name}</p>
	<p>Total investment needed: ${project.desc.cost-investedAmount}</p>
	<p>Minimal investment: ${project.desc.minInvestment}</p>

	<spring:bind path="sum">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<form:input type="text" path="sum" class="form-control"
						placeholder="Your investment"></form:input>
			<form:errors path="sum"></form:errors>
		</div>
	</spring:bind>

	<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
</form:form>

</body>
</html>
