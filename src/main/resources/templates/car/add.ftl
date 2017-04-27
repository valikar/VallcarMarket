[#ftl]
[#import "/spring.ftl" as spring /]

<html lang="en">
<title>Add Car Page</title>
<head>
[#include '/macro/bootstrap_header.ftl']
[#include '/macro/header.ftl']

</head>
<body>
[#include '/macro/nav_index_bar.ftl']
[#include '/macro/errors.ftl']

	<div class="container">

		<form method="post" action="/car/save">
			<div class="row">
				<div class="col-lg-6">
					<label>Manufacturer</label>
						<input name="manufacturer" type="input" value="${car.manufacturer!''}" class="form-control" placeholder="Manufacturer">
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
						<input name="fabricationYear" type="input" value="${car.fabricationYear!''}" class="form-control" placeholder="1900">
				</div>
				<div class="col-lg-6">
					<label>Mileage</label>
						<input name="mileAge" type="input" value="${car.mileAge!''}" class="form-control" placeholder="120000">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-lg-6">
					<label>Engine Type</label>
						<input name="engineType" type="input" value="${car.engineType!''}" class="form-control" placeholder="Engine Type">
				</div>
				<div class="col-lg-6">
					<label>Transmission Type</label>
						<input name="transmissionType" type="input" value="${car.transmissionType!''}" class="form-control" placeholder="Transmission Type">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-lg-6">
					<label>Color</label>
						<input name="colour" type="input" value="${car.color!''}" class="form-control" placeholder="Color">
				</div>
				
			</div>
			<br>
			<div class="row">
				<div class="col-lg-6">
					<label for="price">Price</label>
					<input name="price" type="input" value="${car.price!''}" class="form-control" placeholder="Price">
				</div>
				<div class="col-lg-6">
					<div class="form-group">
					    <label for="carPhoto">Car Image</label>
					   		<input type="file" id="carPhoto">
					    <p class="help-block">Insert an image of the car here.</p>
				    </div>
				</div>
			</div>


			<br>
			<div class="row">
				<div class="col-lg-12">
					<label>Description</label>
					
						<textarea name="extras" type="input" value="${car.extras!''}" class="form-control" rows="3" placeholder="Description"></textarea>
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


	</div>


[#include '/macro/bootstrap_footer.ftl']

</body>
</html>