<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Investments Interests</title>

    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/theme.css">

</head>
<body>
<c:set var="listsize" value="${fn:length(inv_interest_list)}"/>
<div class="container">
    <div class="jumbotron">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <h2>Welcome ${pageContext.request.userPrincipal.name} |
                <a href="#" onclick="document.forms['logoutForm'].submit()">Logout</a>
            </h2>

            <h2>Your investments interests</h2>

        </c:if>

        <c:if test="${pageContext.request.userPrincipal.name == null}">
        <h2>Investments Interests</h2>
            <div>
                <a href="${contextPath}/user/login">Log in</a> to add and investment interest
            </div>
        </c:if>


        <c:if test="${listsize == 0}">
            <div>
                <p>No investments interests. Do you want to adding new?</p>
                <a href="${contextPath}/invinterest/add"><button type="button" class="btn btn-success">Add an investment interest
                </button></a>
            </div>
        </c:if>
    </div>

    <c:if test="${listsize > 0}">
        <div class="col-md-6 contacts-table">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">Investments Interests</h3>
                </div>
                <div class="panel-body">
                    <div class="add-contact">
                        <a href="${contextPath}/invinterest/add"><button type="button" class="btn btn-success">Add an investment interest
                        </button></a>
                    </div>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Goal</th>
                            <th>Category</th>
                            <th>Description</th>
                            <th>Amount of Investment</th>
                            <sec:authorize access="isAuthenticated()">
                                <th>Update</th>
                                <th>Delete</th>
                            </sec:authorize>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${inv_interest_list}" var="invinterest">
                            <tr>
                                <td>${invinterest.id}</td>
                                <td>${invinterest.investmentGoal}</td>
                                <td>${invinterest.category.name}</td>
                                <td>${invinterest.description}</td>
                                <td>${invinterest.amountInvestment}</td>
                                <sec:authorize access="isAuthenticated()">
                                <td><a href="${contextPath}/invinterest/${invinterest.id}/update"><button type="button" class="btn btn-sm btn-info">Update</button></a></td>
                                <td><a href="${contextPath}/invinterest/${invinterest.id}/delete"><button type="button" class="btn btn-sm btn-danger">Delete</button></a></td>
                                </sec:authorize>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:if>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>