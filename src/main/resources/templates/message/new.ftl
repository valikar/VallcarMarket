[#ftl]
<html lang="en">
<title>New Message Page</title>
<head>
[#include '/macro/bootstrap_header.ftl']
[#include '/macro/header.ftl']
</head>
<body>
[#include '/macro/nav_index_bar.ftl']

<div class="panel panel-default" style="margin-left:auto; margin-right:auto; width:900px">
    <div class="panel-heading">
        <div class="g-row u-margin-bottom-9">
            <div class="g-col-6">
                <strong align="center">${conversation.title} </strong>
            </div>
            <div class="g-col-6">
                <h2 class="panel-title" align="right">
                    <strong>To:   ${conversation.receiverName}</strong>
                </h2>
            </div>
        </div>

    </div>

    <div class="panel-body">


        <form method="post" action="/account/message/list/conversation/reply?id=${conversation.id?c}" >
            <div class="form-group">
                <label for="message">Message</label>
                <textarea type="text"
                          class="form-control" id="message" name="message"
                          autofocus="true" value="${message.message}"
                          rows="3" cols="4"></textarea>
            </div>


    <div class="container-fluid">
        <div class="col-lg-6 col-lg-offset-3 text-center">
            <button type="submit" class="btn btn-default">SEND</button>
        </div>
    </div>
    <br>
    </form>

</div>
</div>



[#include '/macro/bootstrap_footer.ftl']
</body>
</html>