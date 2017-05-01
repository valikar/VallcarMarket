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
                <th>Fabrication Year   </th>
                <th>Mileage   </th>
                <th>Price   </th>
                <th>Engine Type </th>
                <th>Transmission Type </th>
                <th>Colour </th>
                <th>Extras </th>
                <th>Seller Id </th>
                <th>Car Id </th>
            </tr>

        [#list cars as car]
            <tr>
                <td>${car.manufacturer}</td>
                <td>${car.type}</td>
                <td>${car.fabricationYear}</td>
                <td>${car.mileAge}</td>
                <td>${car.price}</td>
                <td>${car.engineType}</td>
                <td>${car.transmissionType}</td>
                <td>${car.colour}</td>
                <td>${car.extras}</td>
                <td>${car.sellerId}</td>
                <td>${car.id}</td>
            </tr>
        [/#list]
        </table>

    </div>
</div>

[#include '/macro/bootstrap_footer.ftl']
</body>
</html>