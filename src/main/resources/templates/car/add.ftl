[#ftl]
[#import "/spring.ftl" as spring /]

<html lang="en">
<title>[#if car.id > 0]Edit car page[#else]Add car page[/#if]</title>
<head>
[#include '/macro/bootstrap_header.ftl']
[#include '/macro/header.ftl']

</head>
<body onload="brandChanged();onLoad();">
[#include '/macro/nav_index_bar.ftl']
[#include '/macro/errors.ftl']

<div class="container">

    <form method="post" action="/car/save" enctype="multipart/form-data">
        <div class="row">
            <div class="col-lg-6">
                <label>Manufacturer</label>
                <select name="manufacturer" id="manufacturer" onchange="autoEnable();brandChanged();" class="form-control">
                    <option disabled [#if car.manufacturer??][#else]selected[/#if]>-- Select an option --</option>
                        [#list map?keys as manufacturer]
                            <option value="${manufacturer}" [#if car.manufacturer?? && car.manufacturer == manufacturer]selected[/#if]>${manufacturer}</option>
                        [/#list]
                </select>
            </div>
            <div class="col-lg-6">
                <label>Type</label>
                <select name="type" id="type"  class="form-control">
                </select>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-6">
                <label>Fabrication Year</label>
                <input id="fabricationYear" name="fabricationYear" type="number" value="[#if car.fabricationYear != 0]${car.fabricationYear?string["0000"]}[/#if]" class="form-control"
                       placeholder="From 1900 to present">
            </div>
            <div class="col-lg-6">
                <label>Mileage</label>
                <input id="mileAge" name="mileAge" type="number" value="[#if car.mileAge != 0]${car.mileAge?string["0"]}[/#if]" class="form-control" placeholder="In Kilometers">
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-6">
                <label>Engine Type</label>
                <div class="input-group">
                      <span class="input-group-addon">
                        <input id="radioPETROL" type="radio" name="engineType" value="PETROL"
                               [#if car.engineType?? && car.engineType == 'PETROL']checked[/#if]>Petrol</input>
					  </span>
                    <span class="input-group-addon">
                          <input id="radioDIESEL" type="radio" name="engineType" value="DIESEL"
                                 [#if car.engineType?? && car.engineType == 'DIESEL']checked[/#if]>Diesel</input>
                      	</span>
                    <span class="input-group-addon">
                         	 <input id="radioHYBRID" type="radio" name="engineType" value="HYBRID"
                                    [#if car.engineType?? && car.engineType == 'HYBRID']checked[/#if]>Hybrid</input>
                      		</span>
                    <span class="input-group-addon">
                      		 	   <input id="radioELECTRIC" type="radio" name="engineType" value="ELECTRIC"
                                          [#if car.engineType?? && car.engineType == 'ELECTRIC']checked[/#if]>Electric</input>
								</span>
                </div>
            </div>
            <div class="col-lg-6">
                <label>Transmission Type</label>
                <div class="input-group">
                      <span class="input-group-addon">
                        <input id="radioMANUAL" type="radio" name="transmissionType" value="MANUAL"
                               [#if car.transmissionType?? && car.transmissionType == 'MANUAL']checked[/#if]>Manual</input>
					  </span>
                    <span class="input-group-addon">
                          <input id="radioAUTOMATIC" type="radio" name="transmissionType" value="AUTOMATIC"
                                 [#if car.transmissionType?? && car.transmissionType == 'AUTOMATIC']checked[/#if]>Automatic</input>
                      	</span>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-6">
                <label>Colour</label>
                <select name="colour" id="colour" class="form-control">
                    <option disabled [#if car.colour??][#else]selected[/#if]>-- Select an option --</option>
                        [#list colours as colour]
                            <option value="${colour}" [#if car.colour?? && car.colour == colour]selected[/#if]>${colour}</option>
                        [/#list]
                </select>
            </div>
            <div class="col-lg-6">
                <label>Matriculation status</label>
                <div class="input-group">
                                <span class="input-group-addon">
									<input id="radioMATRICULATIONtrue" type="radio" name="matriculated"
                                           value="true"[#if car.matriculated?? && car.matriculated == true]checked[/#if]>Yes, the car is matriculated</input>
                                </span>
                    <span class="input-group-addon">
                       				<input id="radioMATRICULATIONfalse" type="radio" name="matriculated"
                                           value="false"[#if car.matriculated?? && car.matriculated == false]checked[/#if]>No, the car isn't matriculated</input>
									 </span>
                </div>

            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-6">
                <label for="price">Price</label>
                <input id="price" name="price" type="number" value="[#if car.price != 0]${car.price?string["0000"]}[/#if]" class="form-control" placeholder="Price">
            </div>
            <div class="col-lg-6">
                [#if car.imgUrl??]
                <div class="form-group">
                    <label>Car Image</label> <br>
                    <img src="/ext-img/${car.imgUrl}" style="height: auto; width: 350;"/> <br>
                    <label for="carPhoto">Choose another image</label>
                    <input type="file" id="carPhotoEdit" name="file" accept="image/*">
                </div>
                    [#else]
                        <div class="form-group">
                            <label for="carPhoto">Car Image</label>
                            <input type="file" id="carPhotoSave" name="file" accept="image/*">
                            <p class="help-block">Insert an image of the car here.</p>
                        </div>
                [/#if]
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-12">
                <label for="extras">Description</label>
                <textarea id="extras"  type="text" class="form-control" rows="3" cols="4" name="extras"
                          placeholder="Description">[#if car.extras??]${car.extras}[/#if]</textarea>
            </div>
        </div>
        <br>

        <input name="longitude" type="hidden" value="${carLocation.longitude?c}"/>
        <input name="latitude" type="hidden" value="${carLocation.latitude?c}"/>

        <div class="row">
            <div class="col-lg-12 button-holder">
            [#if car.id??]
                <input name="id" type="hidden" value="${car.id?c}"/>
            [/#if]
                <button class="btn btn-default btn-lg"
                        type="submit">[#if car.id > 0]Save changes[#else]Add car[/#if]</button>
            </div>
        </div>
    </form>
    <br>

[#if message??]${message}[/#if]
</div>
<script>
    [#assign car_manufacuters = map?keys]
    var DATA = {
    [#list car_manufacuters as car_manufacturer]
        '${car_manufacturer}' : [ [#list map[car_manufacturer] as type]'${type}',[/#list] ],
    [/#list]
    };

    function brandChanged() {
        var manufacturers = document.getElementById('manufacturer');
        var brand = manufacturers.options[manufacturers.selectedIndex].value;
        var models = DATA[brand];
        var modelSelect = document.getElementById('type');
        modelSelect.style.display = 'block';
        modelSelect.innerHTML = '';

        var model = '-- Select an option --';
        var opt = document.createElement('option');
        opt.value = model;
        opt.innerHTML = model;
        modelSelect.appendChild(opt);

        for (var i = 0; i < models.length; i++) {
            var model = models[i];
            var opt = document.createElement('option');
            opt.value = model;
            opt.innerHTML = model;
            modelSelect.appendChild(opt);
        }
        document.getElementById('type').options[0].disabled = true;
    }
    function onLoad() {
        var manufacturer = document.getElementById('manufacturer').value;
        if(manufacturer != "-- Select an option --") {
            document.getElementById('type').value = "[#if car.type??]${car.type}[/#if]";
        } else {
            document.getElementById('type').value = "-- Select an option --";
        }
    }
</script>
<script src="/js/add.js"></script>

[#include '/macro/bootstrap_footer.ftl']
[#include '/macro/footer-custom-scripts-for-add-car.ftl']

</body>
</html>