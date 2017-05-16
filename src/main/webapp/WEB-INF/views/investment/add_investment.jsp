<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add Investment</title>
</head>
<body>

<%--<form:form method="POST" modelAttribute="sum" class="form-signin">--%>
<form method="post" action="/project/${project.id}/addInvestment">

<h2 class="form-signin-heading">Add an investment</h2>
    <p>Here you can add you investment in ${project.desc.name}</p>
    <p>Total investment needed: ${project.desc.cost-investedAmount}</p>
    <p>Minimal investment: ${project.desc.minInvestment}</p>

    <span>${message}</span>
    <input type="number" name="sum" placeholder="Your investment"/><br>
    <%--<form:input type="text" path="sum" class="form-control"--%>
                <%--placeholder="Your investment"></form:input>--%>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
</form>
<%--</form:form>--%>

</body>
</html>
