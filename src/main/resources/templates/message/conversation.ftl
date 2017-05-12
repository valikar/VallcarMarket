[#ftl]
<html lang="en" xmlns="http://www.w3.org/1999/html">
<title>Conversation Page</title>
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
                    [#if currentUser.id == conversation.senderId]
                        <strong>To:   ${conversation.receiverName}</strong>
                    [#else ]
                        <strong>From:   ${conversation.senderName}</strong>
                    [/#if]
                </h2>
            </div>
        </div>

    </div>

    <div class="panel-heading" style="height: 32px;">
    [#if currentUser.id == conversation.senderId]
        <p class="align--left">You</p>
        <p class="align--right">${conversation.receiverName}</p>
    [#else ]
        <p class="align--left">You</p>
        <p class="align--right">${conversation.senderName}</p>
    [/#if]
    </div>

     <div class="panel-body"style="max-height: 285;overflow-y: scroll;">
     [#if currentUser.id == conversation.senderId]
         <div class="chat">
             [#list messages as message]
                     [#if conversation.senderId==message.senderId]
                     <li class="self">
                     <p class="chat-message">
                     ${message.message}
                         <time>${message.time}</time>
                     </p>
                     </li>
                     [#else]
                     <li class="other">
                     <p class="chat-message">
                         ${message.message}
                        <time>${message.time}</time>
                     </p>
                     </li>
                     [/#if]
             [/#list]
     </div>
     [#else ]
            <div class="chat">

             [#list messages as message]
                     [#if conversation.receiverId==message.senderId]
                     <li class="other">
                     <p class="chat-message">
                     ${message.message}
                         <time>${message.time}</time>
                     </p>
                     </li>
                     [#else]
                     <li class="self">
                     <p class="chat-message">
                         ${message.message}
                             <time>${message.time}</time>
                     </p>
                     </li>
                     [/#if]
             [/#list]
            </div>
     [/#if]
    </div>
    <div class="panel panel-footer">
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
                    <button type="submit" class="btn btn-default">Reply</button>
                </div>
            </div>
        </form>
    </div>
</div>



[#include '/macro/bootstrap_footer.ftl']
</body>
</html>