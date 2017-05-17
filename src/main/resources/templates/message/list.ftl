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
    <div class="panel-heading" ><b>You have ${conversations?size} conversation[s]</b></div>

    <div class="panel-body">
        <table class="table">
            <tr>
                <th>Title</th>
                <th>With</th>
                <th></th>
                <th>Last Message At </th>
                <th>Conversation </th>
            </tr>

        [#list conversations as conversation]
            <tr>
                <td>${conversation.title}</td>
            [#if currentUser.id!=conversation.senderId]
                <td>${conversation.senderName}</td>
            [#else]
                <td>${conversation.receiverName}</td>
            [/#if]
                <td></td>
                <td>${conversation.lastMessage}</td>
                <td><a href="/account/message/list/conversation?id=${conversation.id?c}">
                    <span style="margin-left: 40px;" class="glyphicon glyphicon-comment" aria-hidden="true"></span></a>
                </td>
            </tr>
        [/#list]
        </table>
    </div>
</div>

[#include '/macro/bootstrap_footer.ftl']
</body>
</html>