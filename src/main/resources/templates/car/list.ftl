[#ftl]
[#import "/spring.ftl" as spring /]

<html lang="en">
<title>Your Cars Page</title>
<head>
[#include '/macro/bootstrap_header.ftl']
[#include '/macro/header.ftl']

</head>
<body>
[#include '/macro/nav_index_bar.ftl']

<div class="panel panel-default" style="margin-left:auto; margin-right:auto; width:1000px">
    <b>Cars List:</b><br>
You have ${cars?size} car[s] in the system
    <div class="panel-body">
        <table class="table">
            <tr>
                <th>Manufacturer</th>
                <th>Type</th>
                <th>Price</th>
                <th></th>
                <th>View </th>
                <th>Edit </th>
                <th>Check In/Out</th>
                <th>Checked In</th>
            </tr>

        [#list cars as car]
            <tr>
                <td>${car.manufacturer}</td>
                <td>${car.type}</td>
                <td>${car.price}</td>
                <td></td>
                <td><a href="/account/list/car?id=${car.id?c}">
                    <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></a>
                </td>
                <td> <a href="/account/list/car/edit?id=${car.id?c}">
                    <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span></a>
                </td>
                <td>
                    Aici vine un buton sau ceva
                </td>
                <td>
                    <input type="checkbox"onclick="return false;" onkeydown="return false;" [#if car.available==false]unchecked[#else ]checked[/#if]/>
                </td>
            </tr>
        [/#list]
        </table>
    </div>
</div>

[#include '/macro/bootstrap_footer.ftl']
</body>
</html>