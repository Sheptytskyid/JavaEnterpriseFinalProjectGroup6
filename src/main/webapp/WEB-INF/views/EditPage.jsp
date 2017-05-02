<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
            <a class="navbar-brand" href="#">Great<i class="fa fa-rocket fa-lg" aria-hidden="true">
                <text/>
            </i>Start</a>
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
                        <img src="http://vev.ru/uploads/images/00/07/64/2011/10/12/c26b1b.jpg"
                             class="img-responsive img-circle" title="UserName"
                             alt="UserName" width="30px" height="30px"/>
                    </span>
                    <span>UserName</span>
                    <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <div class="navbar-content">
                                <div class="row">
                                    <div class="col-md-5">
                                        <img src="http://vev.ru/uploads/images/00/07/64/2011/10/12/c26b1b.jpg"
                                             alt="AltText" class="img-responsive"
                                             width="120px" height="120px"/>
                                    </div>
                                    <div class="col-md-7">
                                        <span>Welcome back,</span>
                                        <p class="text-muted">UserName!</p>
                                        <div class="btn-group-vertical" style="margin-top: -30px">
                                            <a href="#" class="btn btn-link btn-md"><i class="fa fa-user-o"
                                                                                       aria-hidden="true"
                                                                                       style="margin-right: 5px"></i>My
                                                Account</a>
                                            <a href="#" class="btn btn-link btn-md"><i class="fa fa-star"
                                                                                       aria-hidden="true"
                                                                                       style="margin-right: 5px"></i>Favourites</a>
                                            <a href="#" class="btn-link btn btn-md"><i class="fa fa-question-circle-o"
                                                                                       style="margin-right: 5px"></i>Help
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="navbar-footer">
                                <div class="navbar-footer-content">
                                    <div class="row">
                                        <div class="col-md-6 pull-right">
                                            <a href="#" class="btn btn-default btn-block"><i class="fa fa-sign-out"
                                                                                             aria-hidden="true"></i>
                                                Sign
                                                Out</a>
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
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h4>User Profile</h4>
                    <a class="btn btn-edit pull-right" href="DesignPage1.html"><h4><i
                            class="glyphicon glyphicon-pencil"></i>Edit</h4>
                    </a>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-4 col-md-4 col-sm-4  hidden-xs">
                            <img class="img-responsive img-rounded big-photo"
                                 src="http://vev.ru/uploads/images/00/07/64/2011/10/12/c26b1b.jpg"/>
                        </div>
                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                            <div class="panel-info">
                                <div class="panel-body">
                                </div>
                                <form method="POST" id="user_form"></form>
                                <table class="table">
                                    <tr>
                                        <th>First name:</th>
                                        <td>
                                            <input type="text" placeholder="First name" class="form-control input-md"
                                                   form="user_form">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>Last name:</th>
                                        <td>
                                            <input type="text" placeholder="Last name" class="form-control input-md"
                                                   form="user_form">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>Email:</th>
                                        <td>
                                            <input type="text" placeholder="email@example.com"
                                                   class="form-control input-md" form="user_form">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>Phone:</th>
                                        <td>
                                            <input type="text" placeholder="" class="form-control input-md"
                                                   form="user_form">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>Address:</th>
                                        <td>
                                            <input type="text" placeholder="" class="form-control input-md"
                                                   form="user_form">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <td>
                                            <div class="btn-group">
                                                <button  class="btn btn-success"
                                                         type="submit" form="user_form">Save changes
                                                </button>
                                                <button class="btn btn-warning" type="reset" form="user_form">Clean
                                                </button>
                                                <a class="btn btn-danger" href="UserPage.html">Cancel</a>
                                            </div>
                                        </td>
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


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>