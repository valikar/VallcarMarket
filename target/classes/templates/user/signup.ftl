[#ftl]
<html lang="en">
<title>SignUp Page</title>
<head>
[#include '/macro/bootstrap_header.ftl']
[#include '/macro/header.ftl']
</head>
<body>
[#include '/macro/nav_signup_bar.ftl']

[#include '/macro/errors.ftl']
<div class="panel panel-default" style="margin-left:auto; margin-right:auto; width:500px">
    <div class="panel-body">


        <form method="post" action="/signup/save" >
            <input type="hidden" name="id" value="[#if user.id??]${user.id?c}[/#if]">
            <div class="form-group">
                <label for="firstName">First Name</label>
                <input type="text"
                       class="form-control" id="firstName" name="firstName"
                       placeHolder="Conor"autofocus="true" value="${user.firstName!''}"/>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <input type="text"
                       class="form-control" id="lastName" name="lastName"
                       placeHolder="McGregor"value="${user.lastName!''}"/>
            </div>
            <div class="form-group">
                <label for="userName">Username</label>
                <input type="text"
                       class="form-control" id="userName" name="userName"
                       placeHolder="conor.mcgregor@gmail.com"value="${user.userName!''}"/>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password"
                       class="form-control" id="password" name="password"
                       placeHolder="password"value="${user.password!''}"/>
            </div>
            <div class="form-group">
                <label for="passwordValidation">Password Validation</label>
                <input type="password"
                       class="form-control" id="passwordValidation" name="passwordValidation"
                       placeHolder="password" value="${user.passwordValidation!''}" onkeyup="checkPass(); return false;"/>
            </div>
            <div class="panel-body">
                    <div class="btn-group-justified">
                        <div class="input-group">
                            <span class="input-group-addon">
                             <input type="radio" id="radioBUYER" name="role" value="BUYER" [#if user.role?? && user.role == 'BUYER']checked[/#if]>BUYER</input>
                                <input type="radio" id="radioSELLER" name="role" value="SELLER" [#if user.role?? &&  user.role == 'SELLER']checked[/#if]>SELLER</input>
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
[#include '/macro/footer-custom-scripts-for-signup.ftl']
</body>
</html>