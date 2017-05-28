[#ftl]
<html lang="en">


<head>
    <title>Index Page</title>
    [#include '/macro/bootstrap_header.ftl']
    [#include '/macro/header.ftl']
    <link rel="stylesheet" type="text/css" href="/css/index.css">
</head>
<body>
[#assign page = 'index']
[#include '/macro/nav_index_bar.ftl']

<div class="container">
    <div class="jumbotron">
        <h1><i class="fa fa-car" aria-hidden="true"></i> Car Market Management</h1>
        <p>Find a car you like. Locate it or contact the seller. Buy it.</p>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <h3>[#if cars?size != 0]Last Cars Added:[#else]There are no cars in the system. To add cars please login or sign up.[/#if]</h3>
        </div>
    </div>
    <hr>
    <div class="row text-center">
        [#list cars as car]
            <div class="col-md-4 hero-feature">
                <div class="thumbnail">
                    <img src="[#if car.imgUrl??]/ext-img/${car.imgUrl}[#else]/images/car-placeholder.jpg[/#if]" class="img-fluid" alt="">
                    <div class="caption">
                        <h3>${car.manufacturer} ${car.type}</h3>
                        [#--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>--]
                        <p>
                            <a href="/account/list/car?id=${car.id?c}" class="btn btn-default"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> View car</a>
                            [#if currentUser?? && currentUser.id!= car.sellerId]
                                <a href="/account/list/car?id=${car.id?c}" class="btn btn-default"><span class="glyphicon glyphicon-heart" aria-hidden="true" ></span> Bookmark</a>
                                <a href="/account/list/car?id=${car.id?c}" class="btn btn-default"><span class="glyphicon glyphicon-comment" aria-hidden="true"></span> Contact</a>
                            [/#if]
                        </p>
                        [#--<p>--]

                                [#--<a href="/account/list/car?id=${car.id?c}" style="margin-left: 9px; margin-right: 9px">View--]
                                    [#--<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></a>--]
                                [#--<a href="/account/bookmark?id=${car.id?c}" style="margin-left: 9px; margin-right: 9px">Bookmark--]
                                    [#--<span class="glyphicon glyphicon-heart" aria-hidden="true" ></span></a>--]
                                [#--<a href="/account/list/car/conversation?id=${car.id?c}" style="margin-left: 9px; margin-right: 9px;">Contact--]
                                    [#--<span class="glyphicon glyphicon-comment" aria-hidden="true" ></span>--]
                                [#--</a>--]
                            [#--</p>--]

                        [#--[#else]--]
                            [#--<p align="center" style="margin-left: 100px; margin-right: 100px;">--]
                                [#--<a href="/account/list/car?id=${car.id?c}" >View--]
                                    [#--<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></a></p>--]

                        [#--[/#if]--]
                    </div>
                </div>
            </div>
        [/#list]
    </div>

</div>


[#--<div class="panel panel-default" style="margin-left:auto; margin-right:auto; width:1000px">--]
    [#--[#if currentUser??]--]
    [#--<div class=" panel-heading">--]
        [#--<div class="btn-group btn-group-justified" role="group" aria-label="...">--]
        [#--<a class="btn btn-default" href="/search" role="button"><i class="fa fa-edit" aria-hidden="true"></i>  Make A Search</a>--]
        [#--[#if currentUser.role=='BUYER']--]
        [#--<a class="btn btn-default" href="/account/buyer" role="button">[#else]--]
        [#--<a class="btn btn-default" href="/account/seller" role="button">--]
        [#--[/#if]--]
            [#--<i class="fa fa-list" aria-hidden="true"></i>--]
            [#--View your account for more options--]
            [#--<span style="float: right" class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>--]

        [#--</a>--]
        [#--</div>--]
    [#--</div>--]
    [#--[/#if]--]
    [#--<b style="margin-left: 10px">Last Cars Added:</b><br>--]
    [#--<div class="panel-body" style="display: flex; flex-direction: row;">--]
            [#--[#list cars as car]--]

            [#--<div class="thumbnail" style="margin-right: 25px; margin-left: 25px">--]
                [#--<img src="[#if car.imgUrl??]/ext-img/${car.imgUrl}[#else]/images/car-placeholder.jpg[/#if]" style="height: auto; width: 250;"/>--]
            [#--<div class="caption">--]
                [#--<h3 align="center">${car.manufacturer}  ${car.type}</h3>--]
                [#--<p>--]
                [#--[#if currentUser?? && currentUser.id!= car.sellerId]--]
                    [#--<a href="/account/list/car?id=${car.id?c}" style="margin-left: 9px; margin-right: 9px">View--]
                    [#--<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></a>--]
                        [#--<a href="/account/bookmark?id=${car.id?c}" style="margin-left: 9px; margin-right: 9px">Bookmark--]
                            [#--<span  class="glyphicon glyphicon-heart" aria-hidden="true" ></span></a>--]
                        [#--<a href="/account/list/car/conversation?id=${car.id?c}" style="margin-left: 9px; margin-right: 9px;">Contact--]
                            [#--<span class="glyphicon glyphicon-comment" aria-hidden="true" ></span>--]
                        [#--</a>--]
                [#--</p>--]

                [#--[#else]--]
                    [#--<p align="center" style="margin-left: 100px; margin-right: 100px;">--]
                        [#--<a href="/account/list/car?id=${car.id?c}" >View--]
                        [#--<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></a></p>--]

                [#--[/#if]--]
            [#--</div>--]
            [#--</div>--]
        [#--[/#list]--]
    [#--</div>--]
[#--</div>--]

[#include '/macro/bootstrap_footer.ftl']
</body>
</html>