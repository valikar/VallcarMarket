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
    <div class="panel-heading" ><b>You have ${cars?size} car[s] in the system</b><br>
    </div>
    <div class="panel-body">
        <table class="table">
            <tr>
                <th>Manufacturer</th>
                <th>Type</th>
                <th>Price</th>
                <th></th>
                <th>Views </th>
                <th>View </th>
                <th>Edit </th>
                <th>CheckIn/CheckOut</th>
                <th>Status</th>
            </tr>

        [#list cars as car]
            <tr>
                <td>${car.manufacturer}</td>
                <td>${car.type}</td>
                <td>${car.price}</td>
                <td></td>
                <td>${car.views}</td>
                <td><a href="/account/list/car?id=${car.id?c}">
                    <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></a>
                </td>
                <td> <a href="/car/edit?id=${car.id?c}">
                    <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span></a>
                </td>
                <td>
                    [#if car.available==false]
                        <a href="/account/list/car/checkIn?id=${car.id?c}"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></a>
                    [#else ]
                        <a href="/account/list/car/checkIn?id=${car.id?c}"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                    [/#if]
                </td>
                <td>
                    <input type="checkbox"onclick="return false;" onkeydown="return false;" [#if car.available?? && car.available==false]unchecked[#else ]checked[/#if]/>
                </td>
            </tr>
        [/#list]
        </table>
    </div>
</div>

[#include '/macro/bootstrap_footer.ftl']
[#include '/macro/footer-custom-scripts-for-list.ftl']
</body>
</html>