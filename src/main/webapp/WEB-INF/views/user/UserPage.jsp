<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<spring:url value="/user/photo" var="userPhotoUrl"/>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Profile</title>

    <link rel="shortcut icon" href="${contextPath}/resources/img/favicon.png" type="image/x-icon">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/font-awesome.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/main.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/card.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/action.css" rel="stylesheet">

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
                                                                          style="margin-right: 5px"></span>Help</a></li>
                                                    <li><a href="#" class="btn btn-success btn-md"
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
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="panel panel-success" style="border:0">
                <div class="panel-heading" style="background: #f5f5f5; border: 0">
                    <a class="btn btn-edit pull-right" href="${contextPath}/user/${dtoUserProfile.id}/edit">
                        <span class="glyphicon glyphicon-pencil"></span></a>
                    <h4>User Profile</h4>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-4 col-md-4 col-sm-4  hidden-xs">
                            <img class="img-responsive img-rounded big-photo"
                                 src="${userPhotoUrl}/${dtoUserProfile.id}" alt="User big photo"/>
                        </div>
                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                            <div class="panel-info">
                                <div class="panel-body">
                                </div>
                                <table class="table">
                                    <tr>
                                        <th>First name:</th>
                                        <td><c:out value="${dtoUserProfile.name}"/></td>
                                    </tr>
                                    <tr>
                                        <th>Last name:</th>
                                        <td><c:out value="${dtoUserProfile.lastName}"/></td>
                                    </tr>
                                    <tr>
                                        <th>Email:</th>
                                        <td><c:out value="${dtoUserProfile.email}"/></td>
                                    </tr>
                                    <tr>
                                        <th>Phone:</th>
                                        <td><c:out value="${dtoUserProfile.phoneNumber}"/></td>
                                    </tr>
                                    <tr>
                                        <th>Address:</th>
                                        <td><c:out value="${dtoUserProfile.address}"/></td>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <td></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <a href="#favourites" class="btn btn-success btn-block " data-toggle="collapse">Favourites <span
                    class="fa fa-sort-desc"></span></a>
            <div class="collapse" id="favourites">
                <div class="well">
                    <div class="panel-info">
                        <div class="panel-body">
                            <div class="row masonry" data-columns>
                                <div class="card">
                                    <a href="#"><img class="card-img-top img-responsive"
                                                     src="https://goo.gl/bLja0p" alt=""></a>
                                    <div class="card-block">
                                        <a href="#"><h4 class="card-title">The NOMATIC Backpack and Travel Pack</h4></a>
                                        <div class="card-text">
                                            <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                                                tempor
                                                incididunt ut labore et
                                                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation
                                                ullamco
                                                laboris nisi ut aliquip
                                                ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                                                voluptate
                                                velit
                                                esse cillum dolore eu
                                                fugiat nulla pariatur.
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div>min</div>
                                                <div><span class="fa fa-dollar"></span>100000</div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="right-box">Still needed</div>
                                                <div class="right-box"><span class="fa fa-dollar"></span>9999</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <a href="#"><img class="card-img-top img-responsive"
                                                     src="https://goo.gl/e7PZpL"></a>
                                    <div class="card-block">
                                        <a href="#"><h4 class="card-title">Artificial Intelligence A-Z™: Learn How To
                                            Build An AI</h4></a>
                                        <div class="card-text">
                                            <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                                                tempor
                                                incididunt ut labore et
                                                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation
                                                ullamco
                                                laboris nisi ut aliquip
                                                ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                                                voluptate
                                                velit
                                                esse cillum dolore eu
                                                fugiat nulla pariatur.
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div>min</div>
                                                <div><span class="fa fa-dollar"></span>100000</div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="right-box">Still needed</div>
                                                <div class="right-box"><span class="fa fa-dollar"></span>9999</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <a href="#"><img class="card-img-top img-responsive"
                                                     src="https://goo.gl/bLja0p" alt=""></a>
                                    <div class="card-block">
                                        <a href="#"><h4 class="card-title">The NOMATIC Backpack and Travel Pack</h4></a>
                                        <div class="card-text">
                                            <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                                                tempor
                                                incididunt ut labore et
                                                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation
                                                ullamco
                                                laboris nisi ut aliquip
                                                ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
                                                voluptate
                                                velit
                                                esse cillum dolore eu
                                                fugiat nulla pariatur.
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div>min</div>
                                                <div><span class="fa fa-dollar"></span>100000</div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="right-box">Still needed</div>
                                                <div class="right-box"><span class="fa fa-dollar"></span>9999</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="col-xs-12">
        <div class="bs-calltoaction bs-calltoaction-success">
            <div class="row">
                <div class="col-md-9 cta-contents">
                    <a href="#"><h1 class="cta-title">Projects</h1></a>
                    <div class="cta-desc">
                        <p><a href="#">My Projects</a></p>
                        <p>Describe the action here.</p>
                        <p>Describe the action here.</p>
                    </div>
                </div>
                <div class="col-md-3 form-field">
                    <form class="form-horizontal" action="" method="" id="addProject">
                        <fieldset>
                            <div class="form-group col-md-12">
                                <div>
                                    <input id="ProjectName" name="ProjectName" type="text"
                                           placeholder="Enter project name"
                                           class="form-control input-md input-text">
                                </div>
                                <div class="cta-button">
                                    <button type="submit" class="btn btn-lg btn-block btn-edit">Add Project</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <div class="bs-calltoaction bs-calltoaction-success">
            <div class="row">
                <div class="col-md-9 cta-contents">
                    <a href="#"><h1 class="cta-title">Interests</h1></a>
                    <div class="cta-desc">
                        <p><a href="#">My Interests</a></p>
                        <p>Describe the action here.</p>
                        <p>Describe the action here.</p>
                    </div>
                </div>
                <div class="col-md-3 form-field">
                    <form class="form-horizontal" action="" method="" id="addInterest">
                        <fieldset>
                            <div class="form-group col-md-12">
                                <div>
                                    <select id="addInterestOption" class="select-input"
                                            title="What do you want to invest in?">
                                        <option disabled selected>What do you want to invest in?</option>
                                        <option value="1">Startups and Investment Projects</option>
                                        <option value="2">Venture capital</option>
                                        <option value="3">Mergers and acquisitions</option>
                                    </select>
                                </div>
                                <div class="cta-button">
                                    <button type="submit" class="btn btn-lg btn-block btn-edit">Add Interest</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <div class="bs-calltoaction bs-calltoaction-success">
            <div class="row">
                <div class="col-md-9 cta-contents">
                    <a href="#"><h1 class="cta-title">Events</h1></a>
                    <div class="cta-desc">
                        <p><a href="#">My Events</a></p>
                        <p>Describe the action here.</p>
                        <p>Describe the action here.</p>
                    </div>
                </div>
                <div class="col-md-3 form-field">
                    <form class="form-horizontal" action="" method="" id="addEvent">
                        <fieldset>
                            <div class="form-group col-md-12">
                                <div>
                                    <input id="EventName" name="EventName" type="text"
                                           placeholder="Enter event name"
                                           class="form-control input-md input-text">
                                </div>
                                <div class="cta-button">
                                    <button type="submit" class="btn btn-lg btn-block btn-edit">Add Event</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <div class="container">
        <div class="row centered">
            <a href="#"><span class="fa fa-twitter"></span></a>
            <a href="#"><span class="fa fa-facebook"></span></a>
            <a href="#"><span class="fa fa-vk"></span></a>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
<script src="${contextPath}/resources/js/myJs.js"></script>
<script src="${contextPath}/resources/js/salvattore.min.js"></script>
</body>
</html>