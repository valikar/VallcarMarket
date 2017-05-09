[#ftl]
<html lang="en">
<title>Index Page</title>
<head>
[#include '/macro/bootstrap_header.ftl']
[#include '/macro/header.ftl']
</head>
<body>

[#include '/macro/nav_index_bar.ftl']
<div class="panel panel-default" style="margin-left:auto; margin-right:auto; width:1000px">
    <b>Users List:</b><br>
    ${users?size} user[s] registered
    <div class="panel-body">
        <table class="table">
            <tr>
                <th>First Name   </th>
                <th>Last Name   </th>
                <th>Username   </th>
                <th>Password   </th>
                <th>Role   </th>
                <th>ID  </th>
            </tr>

        [#list users as user]
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.userName}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>${user.id}</td>
            </tr>
        [/#list]
        </table>

    </div>
</div>

<div class="panel panel-default" style="margin-left:auto; margin-right:auto; width:1000px">
    <b>Cars List:</b><br>
${cars?size} car[s] added
    <div class="panel-body">
        <table class="table">
            <tr>
                <th>Manufacturer   </th>
                <th>Type   </th>
                <th>Seller Id </th>
                <th>Car Id </th>
                <th>Views </th>
                <th>Available </th>
            </tr>

        [#list cars as car]
            <tr>
                <td>${car.manufacturer}</td>
                <td>${car.type}</td>
                <td>${car.sellerId}</td>
                <td>${car.id}</td>
                <td>${car.views}</td>
                <td>${car.available?string('Yes', 'No')}</td>
            </tr>
        [/#list]
        </table>

    </div>
</div>


[#include '/macro/bootstrap_footer.ftl']
</body>
</html>