
// set constraint values for input fields
$(document).ready(function() {

    //message required and must be 2 - 165 long any character
    $("#message").attr('required', true);
    $("#message").attr('minlength', 2);
    $("#message").attr('maxlength', 165);
    $("#message").attr('title', "Message should be 2 - 165 characters long");

});