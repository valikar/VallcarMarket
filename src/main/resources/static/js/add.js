function autoEnable() {
	var dropdownlist = document.getElementById("manufacturer");
	var selectedValue = dropdownlist.options[dropdownlist.selectedIndex].value;
	if (selectedValue == "-- Select an option --") {
  	  	disable()
  	} else {
   		enable();
    }
}

function disable() {
    document.getElementById("type").disabled=true;
}
function enable() {
    document.getElementById("type").disabled=false;
}
