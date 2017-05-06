[#ftl]
[#import "/spring.ftl" as spring /]

<html lang="en">
<title>Your Conversations Page</title>
<head>
[#include '/macro/bootstrap_header.ftl']
[#include '/macro/header.ftl']

</head>
<body>
[#include '/macro/nav_index_bar.ftl']

<div class="panel panel-default" style="margin-left:auto; margin-right:auto; width:1000px">
    <b>Conversations List:</b><br>
    You have ${conversations?size} conversation[s]
    <div class="panel-body">
        <table class="table">
            <tr>
                <th>Title</th>
                <th>Sender</th>
                <th></th>
                <th>Conversation </th>
            </tr>

        [#list conversations as conversation]
            <tr>
                <td>${conversation.title}</td>
                <td>${conversation.senderName}</td>
                <td></td>
                <td><a href="/account/message/list/conversation?id=${conversation.id?c}">
                    <span class="glyphicon glyphicon-comment" aria-hidden="true"></span></a>
                </td>
            </tr>
        [/#list]
        </table>
    </div>
</div>

[#include '/macro/bootstrap_footer.ftl']
</body>
</html>