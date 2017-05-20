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

    <title>Projects</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/theme.css">
</head>
<body>
<c:set var="listsize" value="${fn:length(projectList)}"/>
<div class="container">
    <div class="jumbotron">
        <sec:authorize access="isAuthenticated()">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>

            <h2>Welcome ${pageContext.request.userPrincipal.name} |
                <a href="#" onclick="document.forms['logoutForm'].submit()">Logout</a>
            </h2>

        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            <h2>All projects
            </h2>
            <div>
                <a href="${contextPath}/user/login">Log in</a> to add and edit projects
            </div>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
        <c:if test="${listsize == 0}">
            <div>
                <p>No projects yet. Want to start adding them?</p>
                <a href="${contextPath}/project/new"><button type="button" class="btn btn-success">Add a project
                </button></a>
            </div>
        </c:if>
        </sec:authorize>
    </div>

    <c:if test="${listsize > 0}">
        <div class="col-md-6 contacts-table">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title"><c:out value="${listTitle}"/></h3>
                </div>
                <div class="panel-body">
                    <sec:authorize access="isAuthenticated()">
                        <div class="add-contact">
                            <a href="${contextPath}/project/new"><button type="button" class="btn btn-success">Add a project
                            </button></a>
                        </div>
                    </sec:authorize>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Goal</th>
                            <th>Category</th>
                            <th>Cost</th>
                            <th>Description</th>
                            <th>Min. investment</th>
                            <sec:authorize access="isAuthenticated()">
                                <th>Update</th>
                                <th>Delete</th>
                            </sec:authorize>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${projectList}" var="project">
                            <tr>
                                <td>${project.id}</td>
                                <td><a href="/project/${project.id}">${project.desc.name}</a></td>
                                <td>${project.desc.goal}</td>
                                <td>${project.category.name}</td>
                                <td>${project.desc.cost}</td>
                                <td>${project.desc.description}</td>
                                <td>${project.desc.minInvestment}</td>
                                <sec:authorize access="isAuthenticated()">
                                    <td><a href="${contextPath}/project/${project.id}/update"><button type="button" class="btn btn-sm btn-info">Update</button></a></td>
                                    <td><a href="${contextPath}/project/${project.id}/delete"><button type="button" class="btn btn-sm btn-danger">Delete</button></a></td>
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