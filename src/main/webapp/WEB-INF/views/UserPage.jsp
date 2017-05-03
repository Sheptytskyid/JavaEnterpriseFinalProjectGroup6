<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
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
                                    <div class="col-md-5 col-xs-5">
                                        <img src="http://vev.ru/uploads/images/00/07/64/2011/10/12/c26b1b.jpg"
                                             alt="AltText" class="img-responsive"
                                             width="120px" height="120px"/>
                                    </div>
                                    <div class="col-md-7 col-xs-7" style="margin-left: -35px">
                                        <div class="container">
                                            <div>Welcome back,<strong> UserName!</strong></div>
                                            <div class="container"
                                                 style="margin-top:5px; font-size: 15px; font-weight: 500">
                                                <ul class="non-marker">
                                                    <li><a href="#"><i class="fa fa-user-o" aria-hidden="true"
                                                                       style="margin-right: 5px"></i>My account</a></li>
                                                    <li><a href="#"><i class="fa fa-star" aria-hidden="true"
                                                                       style="margin-right: 5px"></i>Favourites</a></li>
                                                    <li><a href="#"><i class="fa fa-question-circle-o"
                                                                       style="margin-right: 5px"></i>Help</a></li>
                                                    <li><a href="#" class="btn btn-success btn-md"
                                                           style="margin-top: 5px"><i class="fa fa-sign-out"
                                                                                      aria-hidden="true"></i>Sign Out</a></li>
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
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h4>User Profile</h4>
                    <a class="btn btn-edit pull-right" href="EditUserPage.html">
                        <h4><i class="glyphicon glyphicon-pencil"></i>Edit</h4>
                    </a>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-4 col-md-4 col-sm-4  hidden-xs">
                            <img class="img-responsive img-rounded big-photo"
                                 src="http://vev.ru/uploads/images/00/07/64/2011/10/12/c26b1b.jpg" alt=""/>
                        </div>
                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                            <div class="panel-info">
                                <div class="panel-body">
                                </div>
                                <table class="table">
                                    <tr>
                                        <th>First name:</th>
                                        <td>User`s First Name</td>
                                    </tr>
                                    <tr>
                                        <th>Last name:</th>
                                        <td>User`s Last Name</td>
                                    </tr>
                                    <tr>
                                        <th>Email:</th>
                                        <td>User`s email</td>
                                    </tr>
                                    <tr>
                                        <th>Phone:</th>
                                        <td>User`s phone</td>
                                    </tr>
                                    <tr>
                                        <th>Address:</th>
                                        <td>User`s address</td>
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
            <a href="#favourites" class="btn btn-info btn-block " data-toggle="collapse">Favourites <span class="fa fa-sort-desc"></span></a>
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
                                                <div><i class="fa fa-dollar"></i>100000</div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="right-box">Still needed</div>
                                                <div class="right-box"><i class="fa fa-dollar"></i>9999</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <a href="#"><img class="card-img-top img-responsive"
                                                     src="https://goo.gl/e7PZpL" alt=""></a>
                                    <div class="card-block">
                                        <a href="#"><h4 class="card-title">Artificial Intelligence A-Z™: Learn How To Build An AI</h4></a>
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
                                                <div><i class="fa fa-dollar"></i>100000</div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="right-box">Still needed</div>
                                                <div class="right-box"><i class="fa fa-dollar"></i>9999</div>
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
                                                <div><i class="fa fa-dollar"></i>100000</div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="right-box">Still needed</div>
                                                <div class="right-box"><i class="fa fa-dollar"></i>9999</div>
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
    <div class="row">
        <div class="col-xs-12">
            <div class="panel panel-warning">
                <div class="panel-heading">
                </div>
                <div class="panel-body">
                    <div class="container">
                        <form class="form-horizontal">
                            <fieldset>
                                <legend>Add new Project</legend>
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="ProjectName">Name</label>
                                    <div class="col-md-4">
                                        <input id="ProjectName" name="ProjectName" type="text" placeholder="Project name"
                                               class="form-control input-md">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="addProject"></label>
                                    <div class="col-md-4">
                                        <div class="btn-group">
                                            <button id="addProject" name="addProject" class="btn btn-success"
                                                    type="submit">Add Project
                                            </button>
                                            <button name="addProject" class="btn btn-warning" type="reset">Clean
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                    <div class="container">
                        <form class="form-horizontal">
                            <fieldset>
                                <legend>Add new Interest</legend>
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="OptionInterest">What do you want to invest
                                        in?</label>
                                    <div class="col-md-4">
                                        <select id="OptionInterest" name="OptionInterest" class="form-control">
                                            <option value="1">Startups and Investment Projects</option>
                                            <option value="2">Venture capital</option>
                                            <option value="3">Mergers and acquisitions</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="addInterest"></label>
                                    <div class="col-md-4 btn-group">
                                        <button id="addInterest" class="btn btn-success" type="submit">Add Interest
                                        </button>
                                        <button class="btn btn-warning" type="reset">Clean</button>
                                    </div>
                                </div>

                            </fieldset>
                        </form>
                    </div>
                    <div class="container">
                        <form class="form-horizontal">
                            <fieldset>
                                <legend>Add new Event</legend>
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="EventName">Name</label>
                                    <div class="col-md-4">
                                        <input id="EventName" name="EventName" type="text" placeholder="Event name"
                                               class="form-control input-md">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="addEvent"></label>
                                    <div class="col-md-4">
                                        <div class="btn-group">
                                            <button id="addEvent" name="addEvent" class="btn btn-success"
                                                    type="submit">Add Event
                                            </button>
                                            <button name="addEvent" class="btn btn-warning" type="reset">Clean
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <div class="container">
        <div class="row centered">
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-vk"></i></a>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
<script src="${contextPath}/resources/js/myJs.js"></script>
<script src="${contextPath}/resources/js/salvattore.min.js"></script>
</body>
</html>