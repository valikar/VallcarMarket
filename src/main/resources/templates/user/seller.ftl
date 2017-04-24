[#ftl]
<html lang="en" xmlns="http://www.w3.org/1999/html">
<title>Seller Account</title>
<head>
[#include '/macro/bootstrap_header.ftl']
[#include '/macro/header.ftl']
</head>
<body>

[#include '/macro/nav_index_bar.ftl']
<div class="panel panel-default" style="margin-left:auto; margin-right:auto; width:700px">
    <div class="panel-body">
        <div class="row">
        <div class="col-lg-4 button-holder">
            <label>Add a new car</label>
            <a class="btn btn-default" href="/car/add" role="button"><i class="fa fa-plus" aria-hidden="true"></i> Add car</a>
        </div>
        <div class="col-lg-4 button-holder">
            <label>View your car list</label>
            <a class="btn btn-default" href="#" role="button"><i class="fa fa-list" aria-hidden="true"></i> Car list</a>
        </div>
        <div class="row">
            <div class="col-lg-4 button-holder">
                <label>Make a search</label>
                <a class="btn btn-default" href="index.html" role="button"><i class="fa fa-search" aria-hidden="true"></i> Search</a>
            </div>
            </div>
    </div>
</div>

[#include '/macro/bootstrap_footer.ftl']
</body>
</html>