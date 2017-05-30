[#ftl]
[#import "/spring.ftl" as spring /]

<html lang="en">

<head>
    <title>Your Bookmarks Page</title>
    [#include '/macro/bootstrap_header.ftl']
    [#include '/macro/header.ftl']
    [#include '/macro/header-custom-scripts-for-bookmarks.ftl']
</head>

<body>

[#include '/macro/nav_index_bar.ftl']
<div class="panel panel-default" style="margin-left:auto; margin-right:auto; width:1000px">
    <div class="panel-heading" ><b>You have ${cars?size} car[s] bookmarked</b><br></div>

    <div class="panel-body">
        <table id="table" class="table">
            <tr>
                <th>Manufacturer</th>
                <th>Type</th>
                <th>Price</th>
                <th></th>
                <th>View </th>
                <th>Contact </th>
                <th>Distance </th>
                <th>Delete </th>
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
                <td><a href="/account/list/car/conversation?id=${car.id?c}" style="margin-left: 15px; margin-right: 3px;">
                        <span class="glyphicon glyphicon-comment" aria-hidden="true" style="margin-right: 5px;"></span></a>
                </td>
                <td >${car.distance}</td>
                <td><a href="/account/bookmark/delete?id=${car.id?c}">
                    <span class="glyphicon glyphicon-erase" aria-hidden="true"></span></a>
                </td>
            </tr>
        [/#list]
        </table>

    </div>
</div>
[#if cars?size == 0]
<div class="panel panel-default" style="border:none;margin-left:auto; margin-right:auto; width:1000px">
    <div class="col-lg-12">
        <label>Looks like you haven't bookmaked any car yet...</label>
         <br>
        <a class="btn btn-default" href="/search" role="button"><i class="fa fa-edit" aria-hidden="true"></i>  Make A Search</a>
    </div>
</div>
[/#if]

[#include '/macro/bootstrap_footer.ftl']

</body>
</html>