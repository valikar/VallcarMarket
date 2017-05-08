[#ftl]
[#import "/spring.ftl" as spring /]

<html lang="en">
<title>Add Car Page</title>
<head>
[#include '/macro/bootstrap_header.ftl']
[#include '/macro/header.ftl']

</head>
<body onload="add()">
[#include '/macro/nav_index_bar.ftl']
[#include '/macro/errors.ftl']

<div class="container">

    <form method="post" action="/car/save" enctype="multipart/form-data">
        <div class="row">
            <div class="col-lg-6">
                <label>Manufacturer</label>
                <input name="manufacturer" type="input" value="${car.manufacturer!''}" class="form-control"
                       placeholder="Manufacturer">
            </div>
            <div class="col-lg-6">
                <label>Type</label>
                <input name="type" type="input" value="${car.type!''}" class="form-control" placeholder="Type">
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-6">
                <label>Fabrication Year</label>
                <input name="fabricationYear" type="input" value="[#if car.fabricationYear != 0]${car.fabricationYear?string["0000"]}[/#if]" class="form-control"
                       placeholder="From 1900 to present">
            </div>
            <div class="col-lg-6">
                <label>Mileage</label>
                <input name="mileAge" type="input" value="[#if car.mileAge != 0]${car.mileAge?string["0000"]}[/#if]" class="form-control" placeholder="In Kilometers">
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-6">
                <label>Engine Type</label>
                <div class="input-group">
                      <span class="input-group-addon">
                        <input type="radio" name="engineType" value="PETROL"
                               [#if car.engineType?? && car.engineType == 'PETROL']checked[/#if]>Petrol</input>
					  </span>
                    <span class="input-group-addon">
                          <input type="radio" name="engineType" value="DIESEL"
                                 [#if car.engineType?? && car.engineType == 'DIESEL']checked[/#if]>Diesel</input>
                      	</span>
                    <span class="input-group-addon">
                         	 <input type="radio" name="engineType" value="HYBRID"
                                    [#if car.engineType?? && car.engineType == 'HYBRID']checked[/#if]>Hybrid</input>
                      		</span>
                    <span class="input-group-addon">
                      		 	   <input type="radio" name="engineType" value="ELECTRIC"
                                          [#if car.engineType?? && car.engineType == 'ELECTRIC']checked[/#if]>Electric</input>
								</span>
                </div>
            </div>
            <div class="col-lg-6">
                <label>Transmission Type</label>
                <div class="input-group">
                      <span class="input-group-addon">
                        <input type="radio" name="transmissionType" value="MANUAL"
                               [#if car.transmissionType?? && car.transmissionType == 'MANUAL']checked[/#if]>Manual</input>
					  </span>
                    <span class="input-group-addon">
                          <input type="radio" name="transmissionType" value="AUTOMATIC"
                                 [#if car.transmissionType?? && car.transmissionType == 'AUTOMATIC']checked[/#if]>Automatic</input>
                      	</span>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-6">
                <label>Colour</label>
                <input name="colour" type="input" value="${car.colour!''}" class="form-control" placeholder="Color">
            </div>
            <div class="col-lg-6">
                <label>Matriculation status</label>
                <div class="input-group">
                                <span class="input-group-addon">
									<input type="radio" name="matriculated"
                                           value="true"[#if car.matriculated?? && car.matriculated == true]checked[/#if]>Yes, the car is matriculated</input>
                                </span>
                    <span class="input-group-addon">
                       				<input type="radio" name="matriculated"
                                           value="false"[#if car.matriculated?? && car.matriculated == false]checked[/#if]>No, the car isn't matriculated</input>
									 </span>
                </div>

            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-6">
                <label for="price">Price</label>
                <input name="price" type="input" value="[#if car.price != 0]${car.price?string["0000"]}[/#if]" class="form-control" placeholder="Price">
            </div>
            <div class="col-lg-6">
                [#if car.imgUrl??]
                <div class="form-group">
                    <label>Car Image</label> <br>
                    <img src="/ext-img/${car.imgUrl}"/> <br>
                    <label for="carPhoto">Choose another image</label>
                    <input type="file" id="carPhoto" name="file" accept="image/*">
                </div>
                    [#else]
                        <div class="form-group">
                            <label for="carPhoto">Car Image</label>
                            <input type="file" id="carPhoto" name="file" accept="image/*">
                            <p class="help-block">Insert an image of the car here.</p>
                        </div>
                [/#if]
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-12">
                <label for="extras">Description</label>
                <textarea id="extras"  type="text" value="[#if car.extras??]${car.extras}[/#if]" class="form-control" rows="3" cols="4" name="extras"
                          placeholder="Description"></textarea>
            </div>
        </div>
        <br>

        <div class="row">
            <div class="col-lg-12 button-holder">
            [#if car.id??]
                <input name="id" type="hidden" value="${car.id?c}"/>
            [/#if]
                <input class="btn btn-default btn-lg" value="Add car" type="submit"/>
            </div>
        </div>
    </form>
    <br>

[#if message??]${message}[/#if]

</div>


[#include '/macro/bootstrap_footer.ftl']
<script>
    function add() {
        document.getElementById("extras").value = "[#if car.extras??]${car.extras}[/#if]";
    }
</script>
</body>
</html>