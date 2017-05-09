[#ftl]
<html lang="en">
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

     <div class="panel-body">
     [#if currentUser.id == conversation.senderId]

         <table class="table">
             <tr>
                 <th>You</th>
                 <th>${conversation.receiverName}</th>
                 <th>Time</th>
             </tr>

             [#list messages as message]
                 <tr>
                     [#if conversation.senderId==message.senderId]
                         <td>${message.message}</td>
                         <td></td>
                         <td>${message.time}</td>
                     [#else]
                         <td></td>
                         <td>${message.message}</td>
                         <td>${message.time}</td>
                     [/#if]
                 </tr>
             [/#list]
         </table>

     [#else ]

         <table class="table">
             <tr>
                 <th>You</th>
                 <th>${conversation.senderName}</th>
                 <th>Time</th>
             </tr>

             [#list messages as message]
                 <tr>
                     [#if conversation.receiverId==message.senderId]
                         <td>${message.message}</td>
                         <td></td>
                         <td>${message.time}</td>
                     [#else]
                             <td></td>
                             <td>${message.message}</td>
                             <td>${message.time}</td>
                     [/#if]
                 </tr>
             [/#list]
         </table>
     [/#if]


         <br>
         <br>
         <br>
         <form method="post" action="/account/message/list/conversation/reply?id=${conversation.id?c}" >

             <div class="form-group">
                 <label for="message">Message</label>
                 <input type="text"
                        class="form-control" id="message" name="message"
                        placeHolder="Enter your message here"autofocus="true" value="${message.message}"/>
             </div>


             <div class="container-fluid">
                 <div class="col-lg-6 col-lg-offset-3 text-center">
                     <button type="submit" class="btn btn-default">Reply</button>
                 </div>
             </div>
             <br>
         </form>

    </div>
</div>



[#include '/macro/bootstrap_footer.ftl']
</body>
</html>