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

    <title>Add an Investment Interest</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>
<body>
<div class="container">

    <form:form method="POST" modelAttribute="investmentinterest" class="form-signin">
        <h2 class="form-signin-heading">Add an Investment Interest</h2>
        <spring:bind path="investmentGoal">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:select type="text" path="investmentGoal" class="form-control" placeholder="Investment goal"
                             autofocus="true">
                    <form:option value="InvestmentGoal.STARTUP">Start-ups and investment projects</form:option>
                    <form:option value="InvestmentGoal.VENTURE">Venture loans to business</form:option>
                    <form:option value="InvestmentGoal.BUSINESS_ACQUISITION">Business acquisition</form:option>
                </form:select>
                <form:errors path="investmentGoal"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="category.name">
            <div class="form-group">
                <form:textarea type="text" path="category.name" class="form-control"
                               placeholder="Category"></form:textarea>
            </div>
        </spring:bind>

        <spring:bind path="description">
            <div class="form-group">
                <form:textarea type="text" path="description" class="form-control"
                               placeholder="Description"></form:textarea>
            </div>
        </spring:bind>

        <spring:bind path="amountInvestment">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="amountInvestment" class="form-control"
                            placeholder="Amount of investment"></form:input>
                <form:errors path="amountInvestment"></form:errors>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>