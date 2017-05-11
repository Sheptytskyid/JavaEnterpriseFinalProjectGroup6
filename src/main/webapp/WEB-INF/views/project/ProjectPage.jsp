<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<spring:url value="/user/photo" var="userPhotoUrl"/>
<c:set var="photo" value="${userPhotoUrl}/${dtoUserProfile.id}"/>
<sec:authorize access="isAuthenticated()">
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Project Page</title>

        <link rel="shortcut icon" href="${contextPath}/resources/img/favicon.png" type="image/x-icon">
        <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
        <link href="${contextPath}/resources/css/font-awesome.css" rel="stylesheet">
        <link href="${contextPath}/resources/css/main.css" rel="stylesheet">
        <link href="${contextPath}/resources/css/menu.css" rel="stylesheet">

        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.js"></script>

        <style type="text/css">
            .pagination > li > a, .pagination > li > span {
                border-radius: 50% !important;
                margin: 0 5px;
            }
        </style>
    </head>

    <body>
    <div class="navbar navbar-default navbar-fixed-top">
        <div class="wrapper" id="wrapper">

            <!-- Sidebar -->
            <nav class="navbar navbar-fixed-top" id="sidebar-wrapper" role="navigation">
                <ul class="nav sidebar-nav">
                    <li class="sidebar-brand">
                        <a href="#">
                            Menu
                        </a>
                    </li>
                    <li>
                        <a href="#">Home</a>
                    </li>
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li>
                        <a href="#">Events</a>
                    </li>
                    <li>
                        <a href="#">Investments</a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Events <span
                                class="caret"></span></a>

                        <ul class="dropdown-menu" role="menu">
                            <li class="dropdown-header">Dropdown heading</li>
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li><a href="#">Separated link</a></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>

                    </li>
                    <li>
                        <a href="#">Interests</a>
                    </li>
                    <li>
                        <a href="#">Contact</a>
                    </li>
                    <li>
                        <a href="#">Follow me</a>
                    </li>
                </ul>
            </nav>
            <!-- /#sidebar-wrapper -->

            <!-- Page Content -->
            <div id="page-content-wrapper">
                <button type="button" class="hamburger is-closed" data-toggle="offcanvas">
                    <span class="hamb-top"></span>
                    <span class="hamb-middle"></span>
                    <span class="hamb-bottom"></span>
                </button>

            </div>
            <!-- /#page-content-wrapper -->
        </div>

        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#responsive-menu">
                    <span class="sr-only">Menu</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Great<i class="fa fa-rocket fa-lg" aria-hidden="true">
                    <text/>
                </i>Start</a>
            </div>
            <div class="collapse navbar-collapse" id="responsive-menu">
                <ul class="nav navbar-nav navbar-left">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#">About</a></li>
                    <li><a href="#">Events</a></li>
                    <li><a href="#">Contacts</a></li>
                    <li>
                        <form role="search" class="navbar-form">
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
                        <c:choose>
                            <c:when test="${dtoUserProfile.photo == null}">
                                <p class="initials-logo-navbar">${dtoUserProfile.initial}</p>
                            </c:when>
                            <c:otherwise>
                        <img src="${photo}"
                             class="img-responsive img-circle" title="UserName"
                             alt="UserName" width="30px" height="30px"/>
                            </c:otherwise>
                        </c:choose>
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
                                                <c:choose>
                                                    <c:when test="${dtoUserProfile.photo == null}">
                                                        <p class="initials-logo-navbar-middle">${dtoUserProfile.initial}</p>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="${photo}"
                                                             alt="AltText" class="img-responsive img-rounded"
                                                             width="120px" height="120px"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <div class="col-md-7 col-xs-7" style="margin-left: -35px">
                                                <div class="container">
                                                    <div>Welcome back,<strong> ${dtoUserProfile.name}!</strong></div>
                                                    <div class="container"
                                                         style="margin-top:5px; font-size: 15px; font-weight: 500">
                                                        <ul class="non-marker">
                                                            <li><a href="#"><span class="fa fa-user-o"
                                                                                  aria-hidden="true"
                                                                                  style="margin-right: 5px"></span>My
                                                                account</a></li>
                                                            <li><a href="#"><span class="fa fa-star" aria-hidden="true"
                                                                                  style="margin-right: 5px"></span>Favourites</a>
                                                            </li>
                                                            <li><a href="#"><span class="fa fa-question-circle-o"
                                                                                  style="margin-right: 5px"></span>Help</a>
                                                            </li>

                                                            <li><a href="#"
                                                                   onclick="document.forms['logoutForm'].submit()"
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

    <div class="container col-xs-12">
        <div class="row">
            <!-- /Investment ITEM -->
            <div class="col-xs-12 col-md-7 col-md-offset-1">
                <div class="item">
                    <div class="thumbnail">
                        <div class="caption centered">
                            <a href="#"><h3>Project name</h3></a>
                            <hr>
                        </div>

                        <a href="#"><img src="http://placehold.it/800x450" alt="" class="img-responsive"></a>
                        <div class="caption">
                            <p>Some project description</p>
                        </div>

                    </div>

                </div>
                <div class="item">
                    <div class="thumbnail">
                        <div>
                            <h3>Category</h3>
                        </div>
                        <div class="caption">
                            <ul>
                                <li>Category 1</li>
                                <li>Category 2</li>
                                <li>Category 3</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="thumbnail">
                        <div>
                            <a href="#"><h3>Current state</h3></a>
                            <hr>
                        </div>
                        <div class="caption">
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                                ut
                                labore
                                et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                                laboris
                                nisi
                                ut
                                aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
                                velit
                                esse
                                cillum dolore eu fugiat nulla pariatur.</p>
                            <hr>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="thumbnail">
                        <div>
                            <a href="#"><h3>Potential</h3></a>
                            <hr>
                        </div>
                        <div class="caption">
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                                ut
                                labore
                                et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                                laboris
                                nisi
                                ut
                                aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
                                velit
                                esse
                                cillum dolore eu fugiat nulla pariatur.</p>
                            <hr>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="thumbnail">
                        <div>
                            <a href="#"><h3>Competitors</h3></a>
                            <hr>
                        </div>
                        <div class="caption">
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                                ut
                                labore
                                et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                                laboris
                                nisi
                                ut
                                aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
                                velit
                                esse
                                cillum dolore eu fugiat nulla pariatur.</p>
                            <hr>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="thumbnail">
                        <div>
                            <a href="#"><h3>Benefits</h3></a>
                            <hr>
                        </div>
                        <div class="caption">
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                                ut
                                labore
                                et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                                laboris
                                nisi
                                ut
                                aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
                                velit
                                esse
                                cillum dolore eu fugiat nulla pariatur.</p>
                            <hr>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="thumbnail">
                        <div>
                            <a href="#"><h3>Bussiness model</h3></a>
                            <hr>
                        </div>
                        <div class="caption">
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                                ut
                                labore
                                et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                                laboris
                                nisi
                                ut
                                aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
                                velit
                                esse
                                cillum dolore eu fugiat nulla pariatur.</p>
                            <hr>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="thumbnail">
                        <div>
                            <a href="#"><h3>Risks</h3></a>
                            <hr>
                        </div>
                        <div class="caption">
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                                ut
                                labore
                                et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                                laboris
                                nisi
                                ut
                                aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
                                velit
                                esse
                                cillum dolore eu fugiat nulla pariatur.</p>
                            <hr>
                        </div>
                    </div>
                </div>
                <!--Window of investments-->
                <div class="item">
                    <div class="hidden-lg hidden-md">
                        <!-- PRICE ITEM -->
                        <!--You had here class called panel-red. I've changed it to panel-danger. If You want return it, so I don`t mind-->
                        <div class="panel price panel-danger">
                            <div class="panel-heading">
                                <h5>Min investment</h5>
                                <h3 class="text-center">$10000</h3>
                            </div>
                            <div class="panel-body text-center">
                                <p class="lead" style="font-size:40px">
                                <h2>Total amount we need $1000000</h2></p>
                            </div>
                            <ul class="list-group list-group-flush text-center">
                                <li class="list-group-item"><i class="icon-ok text-danger"></i> Personal use</li>
                            </ul>
                            <div class="panel-footer">
                                <button type="button" class="btn btn-success btn-block" data-toggle="modal"
                                        data-target="#modal-1">I
                                    want to invest
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-md-offset-8 hidden-sm hidden-xs " style="position: fixed">
                <div class="item">

                    <!-- PRICE ITEM -->
                    <!--The same situation with classes. I mean panel-red to panel-danger-->
                    <div class="panel price panel-danger">
                        <div class="panel-heading text-center">
                            <h3>Min investment $10000</h3>
                        </div>
                        <div class="panel-body text-center">
                            <p class="lead" style="font-size:40px">
                            <h2>Total amount we need $1000000</h2></p>
                        </div>
                        <ul class="list-group list-group-flush text-center">
                            <li class="list-group-item"><i class="icon-ok text-danger"></i> Personal use</li>
                        </ul>
                        <div class="panel-footer">
                            <button type="button" class="btn btn-success btn-block" data-toggle="modal"
                                    data-target="#modal-1">I
                                want to invest
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">

            <div class="footer">
                <div class="container">
                    <div class="row centered">
                        <a href="#"><span class="fa fa-twitter"></span></a>
                        <a href="#"><span class="fa fa-facebook"></span></a>
                        <a href="#"><span class="fa fa-vk"></span></a>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!--Modal-->
    <div class="modal fade" id="modal-invest">
        <div class="modal-dialog">
            <div class="modal-content ">
                <div class="modal-header">
                    <button class="close" type="button" data-dismiss="modal">
                        <i class="fa fa-close"></i>
                    </button>
                    <h4 class="modal-title">Invest in ProjectName</h4>
                </div>
                <div class="modal-body">
                    <p>This page allows you to create a request for investment to ProjectName.</p>
                    <p>Please, select preferred amount of investment.</p>
                    <p>Minimal investment:</p>
                    <p>Total investment needed:</p>
                    <p>Invested in project:</p>

                    <p>Your investment amount</p>
                    <p>Shortly our managers will contact you</p>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-danger" type="button" data-dismiss="modal">Cancel</button>
                    <a href="#" class="btn btn-success" type="submit">Submit</a>
                    <div class="modal-content">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.js"></script>
    <script src="${contextPath}/resources/js/salvattore.min.js"></script>
    <script src="${contextPath}/resources/js/menu.js"></script>
    <script src="${contextPath}/resources/js/slide-down.js"></script>
    </body>
    </html>
</sec:authorize>