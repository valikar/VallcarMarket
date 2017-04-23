[#ftl]
<html>
<head>
	<title>Car Management System</title>
	<link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css"> -->
	<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="add.css">
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	  <div class="container">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="index.html">Car Management System</a>
	    </div>
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li class="active"><a href="index.html">Home</a></li>
	        <li><a href="">About</a></li>
	        <li><a href="">Contact</a></li>
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
	        <li><a href="user.html"><i class="fa fa-user" aria-hidden="true"></i> User</a></li>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>

	<div class="container">
		<div class="jumbotron">
			<h1><i class="fa fa-car" aria-hidden="true"></i> Car Management System</h1>
			<p>Tomato tomato ching chong potato.</p>
		</div>
		<div>
			<div class="row">

			</div>
			<div class="row">
				<div class="col-lg-4 button-holder">
					<label>Make a search</label>
					<a class="btn btn-default" href="index.html" role="button"><i class="fa fa-search" aria-hidden="true"></i> Search</a>
				</div>
				<div class="col-lg-4 button-holder">
					<label>Add a new car</label>
					<a class="btn btn-default" href="addCar.html" role="button"><i class="fa fa-plus" aria-hidden="true"></i> Add car</a>
				</div>
				<div class="col-lg-4 button-holder">
					<label>View your car list</label>
					<a class="btn btn-default" href="#" role="button"><i class="fa fa-list" aria-hidden="true"></i> Car list</a>
				</div>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-lg-12">
				<h2 align="center">Add a new car to the system</h2>
			</div>
		</div>
		<hr>

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
				<div class="col-lg-12">
					<label>Location</label>
					<h2 align="center">Aici o sa vina location-ul</h2>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-lg-12">
					<div class="checkbox">
						<label><input type="checkbox" value="available">Make this ad visible to others.</label>
						<p class="help-block">If you not check this box the ad will appear ONLY in your personal car list. But you can also check it later.</p>
					</div>
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
		<br><br>


	</div>


<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script> -->

</body>
</html>