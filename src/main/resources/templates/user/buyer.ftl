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
        <div class="btn-group btn-group-justified" role="group" aria-label="...">
            <a class="btn btn-default" href="/search" role="button"><i class="fa fa-edit" aria-hidden="true"></i>  Make A Search</a>
            <a class="btn btn-default" href="/account/bookmark/list?id=${currentUser.id?c}" role="button"><i class="fa fa-list" aria-hidden="true"></i>  Your Bookmarks</a>
        </div>
        <br>
        <div class="btn-group btn-group-justified" role="group" aria-label="...">
            <a class="btn btn-default" href="" role="button"><i class="fa fa-envelope" aria-hidden="true"></i>  View Your Messages</a>
        </div>
        <br>
        <div class="btn-group btn-group-justified" role="group" aria-label="...">
            <a class="btn btn-default" href="/account/edit?id=${currentUser.id?c}" role="button"><i class="fa fa-edit" aria-hidden="true"></i>  Edit Your Account</a>
        </div>
</div>
</div>

[#include '/macro/bootstrap_footer.ftl']
</body>
</html>