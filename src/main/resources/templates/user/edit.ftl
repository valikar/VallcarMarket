[#ftl]
<html lang="en">

<head>
    <title>Edit Account Page</title>
    [#include '/macro/bootstrap_header.ftl']
    [#include '/macro/header.ftl']
</head>

<body>
[#include '/macro/nav_index_bar.ftl']

[#include '/macro/errors.ftl']
<div class="panel panel-default" style="margin-left:auto; margin-right:auto; width:500px">
    <div class="panel-body">


        <form method="post" action="/account/save" >

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
            <div class="form-group" style="background-color; grey;">
                <label for="username">Username</label>
                <input type="text"
                       class="form-control" id="userName" name="userName"
                       placeHolder="Username"value="${user.userName!''}" readonly/>
            </div>
            <div class="form-group" style="background-color; grey;">
                <label for="Role">Role</label>
                <input type="text"
                       class="form-control" id="role" name="role"
                       placeHolder="Role"value="${user.role!''}" readonly/>
            </div>
            </div>

        [#if user.id??]
            <input name="id" type="hidden" value="${user.id?c}"/>
            <input name="password" type="hidden" value="${user.password!''}"/>
            <input name="passwordValidation" type="hidden" value="${user.passwordValidation!''}"/>
        [/#if]

            <div class="container-fluid">
                <div class="col-lg-6 col-lg-offset-3 text-center">
                    <button type="submit" class="btn btn-default">SAVE</button>
                </div>
            </div>
        <br>
    </form>

</div>
<div class="container-fluid">
    <div class="col-lg-6 col-lg-offset-3 text-center">
        <a href="/account/edit/password?id=${currentUser.id?c}" class="btn btn-default"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span>Change your password</a>
    </div>
</div>
</div>



[#include '/macro/bootstrap_footer.ftl']
</body>
</html>