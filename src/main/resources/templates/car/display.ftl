[#ftl]
[#import "/spring.ftl" as spring /]

<html lang="en">
<title>Display Car Page</title>
<head>
[#include '/macro/bootstrap_header.ftl']
[#include '/macro/header.ftl']

</head>
<body onload="displayCarPosition(${car.location.latitude?c!''},${car.location.longitude?c!''});">
[#include '/macro/nav_index_bar.ftl']
<div class="panel panel-default" style="margin-right: 10px; margin-left: 10px; margin-top: 15px;">
    <div class="panel-heading">
        <h2 class="panel-title" align="right"><a href="/account/bookmark?id=${car.id?c}">Add to bookmarks
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        </a>
        </h2>
    </div>
    <div class="panel-body">


[#include '/macro/errors.ftl']


<div class="panel panel-default" style=" margin-left:15px;  width:550px">
    <div class="panel-heading">
        <h3 class="panel-title">Technical data</h3>
    </div>
    <div class="panel-body">
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Manufacturer</strong>
            </div>
            <div class="g-col-6">
                <strong>${car.manufacturer}</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Type</strong>
            </div>
            <div class="g-col-6">
                <strong>${car.type}</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Fabrication Year</strong>
            </div>
            <div class="g-col-6">
                <strong>${car.fabricationYear}</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Mileage</strong>
            </div>
            <div class="g-col-6">
                <strong>${car.mileAge} Km</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Price</strong>
            </div>
            <div class="g-col-6">
                <strong>${car.price} $</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Engine Type</strong>
            </div>
            <div class="g-col-6">
                <strong>${car.engineType}</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Transmission Type</strong>
            </div>
            <div class="g-col-6">
                <strong>${car.transmissionType}</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Colour</strong>
            </div>
            <div class="g-col-6">
                <strong>${car.colour}</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Matriculated</strong>
            </div>
            <div class="g-col-6">
                <strong>Edit HERE</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Available</strong>
            </div>
            <div class="g-col-6">
                <strong>Edit HERE</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Extras</strong>
            </div>
        </div>
        <strong>${car.extras}</strong>
    </div>
    </div>
    </div>

    <div class="panel panel-default" style="margin-left:auto; margin-right:auto; width:800px">
        <div class="panel-heading">
            <h3 class="panel-title" align="center" >Location of the car on map</h3>
        </div>
        <div class="panel-body" align="center">
        [#--        <div id="carMarketMapControls">
                    <button type="button" class="btn btn-default" onclick="displayUserPosition();">Display your Position</button>
                    <button type="button" class="btn btn-default" onclick="displayCarPosition(${car.location.latitude!''},${car.location.longitude!''});">Display car Position</button>
                    <button type="button" class="btn btn-default" onclick="displayRoute(${car.location.latitude!''},${car.location.longitude!''});">Display Route</button>
                </div>--]
            <div id="carMarketMap"></div>
        </div>
    </div>

    <div class="panel-footer" align="center">Views: ${car.views}</div>
</div>



[#include '/macro/bootstrap_footer.ftl']
[#include '/macro/footer-custom-scripts-for-display.ftl']
</body>
</html>