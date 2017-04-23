[#ftl]
<html lang="en">
<title>SignUp Page</title>
<head>
[#include '/macro/bootstrap_header.ftl']
[#include '/macro/header.ftl']
</head>
<body>
[#include '/macro/nav_login_bar.ftl']



<div class="panel panel-default" style="margin-left:auto; margin-right:auto; width:500px">

[#include '/macro/errors.ftl']

    <div class="panel-body">


        <form method="post" action="/signup/save" >
            <input type="hidden" name="id" value="[#if user.id??]${user.id?c}[/#if]">
            <div class="form-group">
                <label for="firstName">First Name</label>
                <input type="text"
                       class="form-control" id="firstName" name="firstName"
                       placeHolder="First Name"autofocus="true" value="${user.firstName!''}"/>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <input type="text"
                       class="form-control" id="lastName" name="lastName"
                       placeHolder="Last Name"value="${user.lastName!''}"/>
            </div>
            <div class="form-group">
                <label for="lastName">Username</label>
                <input type="text"
                       class="form-control" id="userName" name="userName"
                       placeHolder="Username"value="${user.userName!''}"/>
            </div>
            <div class="form-group">
                <label for="lastName">Password</label>
                <input type="text"
                       class="form-control" id="password" name="password"
                       placeHolder="Password"value="${user.password!''}"/>
            </div>
            <div class="panel-body">
                    <div class="btn-group-justified">
                        <div class="input-group">
                            <span class="input-group-addon">
                             <input type="radio" name="role" value="BUYER" [#if user.role?? && user.role == 'BUYER']checked[/#if]>BUYER</input>
                                <input type="radio" name="role" value="SELLER" [#if user.role?? &&  user.role == 'SELLER']checked[/#if]>SELLER</input>
                             </span>
                        </div>
                    </div>
                </div>

            <div class="container-fluid">
                <div class="col-lg-6 col-lg-offset-3 text-center">
                    <button type="submit" class="btn btn-default">SAVE</button>
                </div>
            </div>
        </form>

    </div>
</div>



[#include '/macro/bootstrap_footer.ftl']
</body>
</html>