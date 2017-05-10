
// set constraint values for input fields
$(document).ready(function() {
    //manufacturer required
    $("#manufacturer").attr('required', true);
    $("#manufacturer").attr('title', "required field");

    //type required
    $("#type").attr('required', true);
    $("#type").attr('title', "required field");

    //fabricationYear required
    $("#fabricationYear").attr('required', true);
    $("#fabricationYear").attr('min', 1900);
    var now = new Date();
    currentYear = now.getFullYear();
    $("#fabricationYear").attr('max', currentYear);
    $("#fabricationYear").attr('title', "required field");

    //mileage required
    $("#mileAge").attr('required', true);
    $("#mileAge").attr('title', "required field");

    //engine type required
    $("#radioPETROL").attr('required', true);
    $("#radioPETROL").attr('title', "required field");

    //engine type required
    $("#radioDIESEL").attr('required', true);
    $("#radioDIESEL").attr('title', "required field");

    //transmision type required
    $("#radioHYBRID").attr('required', true);
    $("#radioHYBRID").attr('title', "required field");

    //transmision type required
    $("#radioELECTRIC").attr('required', true);
    $("#radioELECTRIC").attr('title', "required field");

    //transmision type required
    $("#radioMANUAL").attr('required', true);
    $("#radioMANUAL").attr('title', "required field");

    //userName required and must respect an email pattern
    $("#radioAUTOMATIC").attr('required', true);
    $("#radioAUTOMATIC").attr('title', "required field");

    //colour required
    $("#colour").attr('required', true);
    $("#colour").attr('title', "required field");

    //matriculation status required
    $("#radioMATRICULATIONtrue").attr('required', true);
    $("#radioMATRICULATIONtrue").attr('title', "required field");

    //matriculation status required
    $("#radioMATRICULATIONfalse").attr('required', true);
    $("#radioMATRICULATIONfalse").attr('title', "required field");

    //price required
    $("#price").attr('required', true);
    $("#price").attr('title', "required field");

    //carPhoto required
    $("#carPhotoSave").attr('required', true);
    $("#carPhotoSave").attr('title', "required field");

    //add interacted class to inputs after user interaction
    var inputs = document.querySelectorAll('input');
    for (var i = 0; i < inputs.length; i++) {
        inputs[i].addEventListener('blur', function(event) {
            event.target.classList.add('interacted');
        }, false);
    }
});