

// set constraint values for input fields
$(document).ready(function() {
    //lastName required and must have 1 - 30 characters from alphabet (a-z)
    $("#firstName").attr('required', true);
    $("#firstName").attr('pattern', "[a-zA-Z ]{3,30}");
    $("#firstName").attr('title', "First name must have alphabet letters (A-Z or a-z) and must be of 3 - 30 characters long");

    //lastName required and must have 1 - 30 characters from alphabet (a-z)
    $("#lastName").attr('required', true);
    $("#lastName").attr('pattern', "[a-zA-Z ]{1,30}");
    $("#lastName").attr('title', "Last name must have alphabet letters (A-Z or a-z) and must be of 3 - 30 characters long");

    //userName required and must respect an email pattern
    $("#userName").attr('required', true);
    $("#userName").attr('pattern', "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$");
    $("#userName").attr('title', "User name must be your email address");

    //password required and must be 6 - 30 long any character
    $("#password").attr('required', true);
    $("#password").attr('pattern', "^.{6,30}$");
    $("#password").attr('title', "Password should be 6 - 30 characters long (any character)");

    //at least one button checked
    $("#radioBUYER").attr('required', true);
    $("#radioSELLER").attr('required', true);


    //add interacted class to inputs after user interaction
    var inputs = document.querySelectorAll('input');
    for (var i = 0; i < inputs.length; i++) {
        inputs[i].addEventListener('blur', function(event) {
            event.target.classList.add('interacted');
        }, false);
    }
});