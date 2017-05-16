[#ftl]
<html lang="en">
<title>Access Denied Page</title>
<head>
[#include '/macro/bootstrap_header.ftl']
[#include '/macro/header.ftl']
</head>
<body>

[#include '/macro/nav_index_bar.ftl']

<div class="panel panel-default" style="margin-left:auto; margin-right:auto; width:1000px">
    <div class="panel-body">

<div class="alert alert-danger" role="alert" align="center">
    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
    <span class="sr-only">Error:</span>
    You have no rights to do that!
</div>
    </div>
</div>
[#include '/macro/bootstrap_footer.ftl']
</body>
</html>