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
    [#if currentUser??]
    <div class=" panel-heading">
        <div class="btn-group btn-group-justified" role="group" aria-label="...">
        <a class="btn btn-default" href="/search" role="button"><i class="fa fa-edit" aria-hidden="true"></i>  Make A Search</a>
        [#if currentUser.role=='BUYER']
        <a class="btn btn-default" href="/account/buyer" role="button">[#else]
        <a class="btn btn-default" href="/account/seller" role="button">
        [/#if]
            <i class="fa fa-list" aria-hidden="true"></i>
            View your account for more options
            <span style="float: right" class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>

        </a>
        </div>
    </div>
    [/#if]
    <b style="margin-left: 10px">Last Cars Added:</b><br>
    <div class="panel-body" style="display: flex; flex-direction: row;">
            [#list cars as car]

            <div class="thumbnail" style="margin-right: 25px; margin-left: 25px">
                <img src="[#if car.imgUrl??]/ext-img/${car.imgUrl}[#else]/images/car-placeholder.jpg[/#if]" style="height: auto; width: 250;"/>
            <div class="caption">
                <h3 align="center">${car.manufacturer}  ${car.type}</h3>
                <p>
                [#if currentUser?? && currentUser.id!= car.sellerId]
                    <a href="/account/list/car?id=${car.id?c}" style="margin-left: 9px; margin-right: 9px">View
                    <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></a>
                        <a href="/account/bookmark?id=${car.id?c}" style="margin-left: 9px; margin-right: 9px">Bookmark
                            <span  class="glyphicon glyphicon-heart" aria-hidden="true" ></span></a>
                        <a href="/account/list/car/conversation?id=${car.id?c}" style="margin-left: 9px; margin-right: 9px;">Contact
                            <span class="glyphicon glyphicon-comment" aria-hidden="true" ></span>
                        </a>
                </p>

                [#else ]
                    <p align="center" style="margin-left: 100px; margin-right: 100px;">
                        <a href="/account/list/car?id=${car.id?c}" >View
                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></a></p>

                [/#if]
            </div>
            </div>
        [/#list]
            </div>
        </div>
    </div>
</div>


[#include '/macro/bootstrap_footer.ftl']
</body>
</html>