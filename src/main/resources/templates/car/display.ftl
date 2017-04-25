[#ftl]
[#import "/spring.ftl" as spring /]

<html lang="en">
<title>Display Car Page</title>
<head>
[#include '/macro/bootstrap_header.ftl']
[#include '/macro/header.ftl']

</head>
<body>
[#include '/macro/nav_login_bar.ftl']

<div class="text-center"><h1>Dacia Logan</h1></div>



[#include '/macro/errors.ftl']
<img src="[@spring.url '/images/car.jpg'/]" style="float: right; margin-right: 50px;" />

<div class="panel panel-default" style=" margin-left:20px;  width:500px">
    <div class="panel-heading">
        <h3 class="panel-title">Technical data</h3>
    </div>
    <div class="panel-body">
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Manufacturer</strong>
            </div>
            <div class="g-col-6">
                <strong>Dacia</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Type</strong>
            </div>
            <div class="g-col-6">
                <strong>Logan</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Fabrication Year</strong>
            </div>
            <div class="g-col-6">
                <strong>2014</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Mileage</strong>
            </div>
            <div class="g-col-6">
                <strong>103.200 Km</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Price</strong>
            </div>
            <div class="g-col-6">
                <strong>500 $</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Engine Type</strong>
            </div>
            <div class="g-col-6">
                <strong>DIESEL</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Transmission Type</strong>
            </div>
            <div class="g-col-6">
                <strong>MANUAL</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Manufacturer</strong>
            </div>
            <div class="g-col-6">
                <strong>Dacia</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Colour</strong>
            </div>
            <div class="g-col-6">
                <strong>Black</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Matriculated</strong>
            </div>
            <div class="g-col-6">
                <strong>Yes</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Available</strong>
            </div>
            <div class="g-col-6">
                <strong>Yes</strong>
            </div>
        </div>
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong>Extras</strong>
            </div>
            <div class="g-col-6">
                <strong>The Dacia Logan is a small family car produced jointly by the French
                    manufacturer Renault and its Romanian subsidiary Dacia since 2004. It is
                    currently in its second generation and has been manufactured at Dacia's
                    automobile plant in Mioveni, Romania, and at Renault's plants in Morocco,
                    Brazil, Argentina, Turkey, Russia, Colombia, Iran and India. It is also
                    produced as a pick-up at Nissan's plant in Rosslyn, South Africa.</strong>
            </div>
        </div>

    </div>
    </div>



    [#include '/macro/bootstrap_footer.ftl']
</body>
</html>