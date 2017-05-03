[#ftl]
<html lang="en" xmlns="http://www.w3.org/1999/html">
<title>Buyer Account</title>
<head>
[#include '/macro/bootstrap_header.ftl']
[#include '/macro/header.ftl']
</head>
<body>

[#include '/macro/nav_index_bar.ftl']
<div class="panel panel-default" style="margin-left:auto; margin-right:auto; width:700px">
    <div class="panel-body">
        <div class="row">
            <div class="row row-clr">
            <div class="col-lg-5 button-holder">
                <label>View your bookmarks</label>
                <a class="btn btn-default" href="/account/bookmark/list?id=${currentUser.id?c}" role="button"><i class="fa fa-list" aria-hidden="true"></i> Car list</a>
            </div>
            <div class="row">
                <div class="col-lg-4 button-holder">
                    <label>Make a search</label>
                    <a class="btn btn-default" href="/search" role="button"><i class="fa fa-search" aria-hidden="true"></i> Search</a>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 button-holder">
                    <label>Edit your account</label>
                    <a class="btn btn-default" href="/account/edit?id=${currentUser.id?c}" role="button"><i class="fa fa-edit" aria-hidden="true"></i> Edit</a>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

[#include '/macro/bootstrap_footer.ftl']
</body>
</html>