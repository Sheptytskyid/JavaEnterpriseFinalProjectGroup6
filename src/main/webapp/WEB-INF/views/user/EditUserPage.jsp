<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<spring:url value="/user/photo" var="userPhotoUrl"/>
<sec:authorize access="isAuthenticated()">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>EditProfile</title>

    <link rel="shortcut icon" href="${contextPath}/resources/img/favicon.png" type="image/x-icon">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/font-awesome.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/main.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#responsive-menu">
                <span class="sr-only">Menu</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Great<span class="fa fa-rocket fa-lg" aria-hidden="true">
                <text/>
            </span>Start</a>
        </div>
        <div class="collapse navbar-collapse" id="responsive-menu">
            <ul class="nav navbar-nav navbar-left">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Events</a></li>
                <li><a href="#">Projects</a></li>
                <li>
                    <form role="search" class="navbar-form navbar-left">
                        <div class="form-group">
                            <input type="text" placeholder="Search" class="form-control">
                        </div>
                    </form>
                </li>
            </ul>
            <sec:authorize access="isAuthenticated()">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown active "><a class="dropdown-toggle" data-toggle="dropdown">
                    <span class="pull-left" style="margin-right:8px; margin-top:-5px;">
                        <img src="${userPhotoUrl}/${dtoUserProfile.id}"
                             class="img-responsive img-circle" title="UserName"
                             alt="UserName" width="30px" height="30px"/>
                    </span>
                        <span>${dtoUserProfile.name}</span>
                        <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li>
                                <div class="navbar-content">
                                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </form>
                                    <div class="row">
                                        <div class="col-md-5 col-xs-5">
                                            <img src="${userPhotoUrl}/${dtoUserProfile.id}"
                                                 alt="AltText" class="img-responsive img-rounded"
                                                 width="120px" height="120px"/>
                                        </div>
                                        <div class="col-md-7 col-xs-7" style="margin-left: -35px">
                                            <div class="container">
                                                <div>Welcome back,<strong> ${dtoUserProfile.name}!</strong></div>
                                                <div class="container"
                                                     style="margin-top:5px; font-size: 15px; font-weight: 500">
                                                    <ul class="non-marker">
                                                        <li><a href="#"><span class="fa fa-user-o" aria-hidden="true"
                                                                              style="margin-right: 5px"></span>My
                                                            account</a></li>
                                                        <li><a href="#"><span class="fa fa-star" aria-hidden="true"
                                                                              style="margin-right: 5px"></span>Favourites</a>
                                                        </li>
                                                        <li><a href="#"><span class="fa fa-question-circle-o"
                                                                              style="margin-right: 5px"></span>Help</a>
                                                        </li>

                                                        <li><a href="#" onclick="document.forms['logoutForm'].submit()"
                                                               class="btn btn-success btn-md"
                                                               style="margin-top: 5px"><span class="fa fa-sign-out"
                                                                                             aria-hidden="true"></span>Sign
                                                            Out</a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </sec:authorize>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="panel panel-success" style="border: 0">
                <div class="panel-heading" style="background: #f5f5f5; border: 0">
                    <a class="btn btn-edit pull-right" href="${contextPath}/user/${dtoUserProfile.id}">
                        <span class="glyphicon glyphicon-pencil"></span></a>
                    <h4>User Profile</h4>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <form:form method="POST" modelAttribute="dtoUserProfile" class="form-signin"
                                   id="user_form" enctype="multipart/form-data" action="/user/${dtoUserProfile.id}/edit">
                        <div class="col-lg-4 col-md-4 col-sm-4  hidden-xs">
                            <div>
                                <img class="img-responsive img-rounded big-photo"
                                     src="${userPhotoUrl}/${dtoUserProfile.id}" alt="User big photo" style="border-radius: 50%"/>
                            </div>
                            <div>
                                <label class="btn btn-default btn-file">
                                 Browse<input accept="image/*" type="file" name="file" style="display: none">
                                </label>
                            </div>
                        </div>
                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                            <div class="panel-info">
                                <div class="panel-body">
                                </div>
                                    <table class="table">
                                        <tr>
                                            <th>First name:</th>
                                            <spring:bind path="name">
                                                <td class="${status.error ? 'has-error' : ''}">
                                                    <form:input type="text" path="name" placeholder="First name"
                                                                class="form-control input-md"
                                                                form="user_form"></form:input>
                                                    <form:errors path="name"></form:errors>
                                                </td>
                                            </spring:bind>
                                        </tr>
                                        <tr>
                                            <th>Last name:</th>
                                            <spring:bind path="lastName">
                                                <td>
                                                    <form:input type="text" placeholder="Last name"
                                                                class="form-control input-md" path="lastName"
                                                                form="user_form"></form:input>
                                                </td>
                                            </spring:bind>
                                        </tr>
                                        <tr>
                                            <th>Email:</th>
                                            <spring:bind path="email">
                                                <td>
                                                    <form:input type="text" placeholder="email@example.com" path="email"
                                                                class="form-control input-md"
                                                                form="user_form" readonly="true"></form:input>
                                                </td>
                                            </spring:bind>
                                        </tr>
                                        <tr>
                                            <th>Phone:</th>
                                            <spring:bind path="phoneNumber">
                                                <td>
                                                    <form:input type="text" placeholder="" class="form-control input-md"
                                                                path="phoneNumber"
                                                                form="user_form"></form:input>
                                                </td>
                                            </spring:bind>
                                        </tr>
                                        <tr>
                                            <th>Address:</th>
                                            <spring:bind path="address">
                                                <td>
                                                    <form:input type="text" placeholder="" class="form-control input-md"
                                                                path="address"
                                                                form="user_form"></form:input>
                                                </td>
                                            </spring:bind>
                                        </tr>
                                        <tr>
                                            <th></th>
                                            <td>
                                                <div class="btn-group">
                                                    <button class="btn btn-success"
                                                            type="submit" form="user_form">Save
                                                    </button>
                                                    <a class="btn btn-danger"
                                                       href="${contextPath}/user/${dtoUserProfile.id}">Cancel</a>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-login">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Login
                    <button class="close" type="button" data-dismiss="modal">
                        <span class="fa fa-close"></span>
                    </button>
                </h4>

            </div>
            <div class="modal-body">
                <form action="${contextPath}/user/login" method="POST">
                    <span>${message}</span>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Email" name="email" autofocus="true">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Password" name="password">
                    </div>
                    <span>${error}</span>
                    <button type="submit" class="btn btn-success">Login</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <form action="/registration">
                        <a class="btn btn-primary" type="submit" href="${contextPath}/user/register">
                            <span class="fa fa-user-circle-o"></span> Create an account
                        </a>
                    </form>

                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-danger" type="button" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
</sec:authorize>