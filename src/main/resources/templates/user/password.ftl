[#ftl]
<html lang="en">

<head>
    <title>Change Password Page</title>
[#include '/macro/bootstrap_header.ftl']
[#include '/macro/header.ftl']
</head>

<body>
[#include '/macro/nav_index_bar.ftl']

[#include '/macro/errors.ftl']
<div class="panel panel-default" style="margin-left:auto; margin-right:auto; width:500px">
    <div class="panel-body">

    <div class="form-group">
        <form method="post" action="/account/savePassword" >

        <label for="password">Password</label>
                <input type="password"
                       class="form-control" id="password" name="password"
                       placeHolder="Password"/>
            </div>
            <div class="form-group">
                <label for="passwordValidation">Password Validation</label>
                <input type="password"
                       class="form-control" id="passwordValidation" name="passwordValidation"
                       placeHolder="Password Validation"/>
            </div>

    [#if user.id??]
        <input name="id" type="hidden" value="${user.id?c}"/>
        <input name="firstName" type="hidden" value="${user.firstName!''}"/>
        <input name="lastName" type="hidden" value="${user.lastName!''}"/>
        <input name="userName" type="hidden" value="${user.userName!''}"/>
        <input name="role" type="hidden" value="${user.role!''}"/>
    [/#if]

        <div class="container-fluid">
            <div class="col-lg-6 col-lg-offset-3 text-center">
                <button type="submit" class="btn btn-default">SAVE</button>
            </div>
        </div>

    </div>
</div>

    [#include '/macro/bootstrap_footer.ftl']
</body>
</html>