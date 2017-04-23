[#ftl]
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Car Management System</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="">Home</a></li>
            </ul>


            <ul class="nav navbar-nav navbar-right">
            <li>[#if currentUser??] <div style="float: right">
                <li><a><span class="glyphicon glyphicon-user" aria-hidden="true"></span>  ${currentUser.userName}</a></li>
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>  LogOut</a></li>
            [#else]
                <li><a href="/login"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>  LogIn</a></li>
                <li><a href="/signup"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>  SignUp</a></li>
            [/#if]
            </ul>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

