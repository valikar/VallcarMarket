
//variables for the position of the center of the Car Market
var carMarketPositionLatitude = 46.7497526;
var carMarketPositionLongitude = 23.428291;
//global variable
var distanceOfRoute;
//variable for car market map
var carMarketMap;

//user position to be random generated later
var userPositionLat;
var userPositionLng;

/*------------------------------------------------------------------------------------------------------------*/
//function to initialize the car market map
function initMap() {
    //options for car market map:
    var isDraggable = $(document).width() > 480 ? true : false;
    var carMarketMapOptions = {
        scrollwheel:false,
        draggable: isDraggable,
        //zoom: 20,
        //center: {lat: carMarketPositionLatitude, lng: carMarketPositionLongitude},
        //mapTypeControl: true,
        //mapTypeControlOptions: {
        //style: google.maps.MapTypeControlStyle.DROPDOWN_MENU,
        //position: google.maps.ControlPosition.RIGHT_TOP
        //},
        navigationControl: false,
        //navigationControlOptions: {
        //style: google.maps.NavigationControlStyle.SMALL,
        //position: google.maps.ControlPosition.LEFT_CENTER
        //},
        mapTypeId: google.maps.MapTypeId.HYBRID
    };
    //initialize the car market map
    carMarketMap = new google.maps.Map(document.getElementById("carMarketMap"), carMarketMapOptions);
    fitToBoundaries(carMarketMap);
    /*  google.maps.event.addListenerOnce(carMarketMap, 'bounds_changed', function(event) {
     var zoom = this.getZoom();
     this.setZoom(zoom+1);
     }
     );
     */}
/*------------------------------------------------------------------------------------------------------------*/

/*------------------------------------------------------------------------------------------------------------*/
//function to set the view to the boundaries of the car market map
function fitToBoundaries(map){
    //boundaries of the Car Market Map
    var NWLatLng = new google.maps.LatLng(46.750770, 23.425043);
    var SWLatLng = new google.maps.LatLng(46.749651, 23.424609);
    var NELatLng = new google.maps.LatLng(46.750696, 23.430960);
    var SELatLng = new google.maps.LatLng(46.749349, 23.430949);
    //NW marker
    var markerNW = new google.maps.Marker({position: NWLatLng,map: map,title: 'NW Marker!'});
    markerNW.setVisible(false);
    //SW marker
    var markerSW = new google.maps.Marker({position: SWLatLng,map: map,title: 'SW Marker!'});
    markerSW.setVisible(false);
    //NE marker
    var markerNE = new google.maps.Marker({position: NELatLng,map: map,title: 'NE Marker!'});
    markerNE.setVisible(false);
    //SE marker
    var markerSE = new google.maps.Marker({position: SELatLng,map: map,title: 'SE Marker!'});
    markerSE.setVisible(false);
    var markers = [markerNW,markerSW,markerNE,markerSE];
    var newBoundary = new google.maps.LatLngBounds();
    for (var i = 0; i < markers.length; i++) {
        var position = markers[i].getPosition();
        newBoundary.extend(position);
    }
    map.fitBounds(newBoundary);
}
/*------------------------------------------------------------------------------------------------------------*/

/*------------------------------------------------------------------------------------------------------------*/
//NOT USED: function to display user position - tobe used later
function displayUserPosition(){
    var userLatLng;
    infoWindow = new google.maps.InfoWindow;
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            function(position) {
                var pos = {
                    //lat: position.coords.latitude,
                    //lng: position.coords.longitude

                    //user position hardcoded to cenetr of market
                    lat:carMarketPositionLatitude,
                    lng: carMarketPositionLongitude
                };
                //set user position
                //userPositionLat = position.coords.latitude;
                //userPositionLng = position.coords.longitude;

                //user position hardcoded to cenetr of market
                userPositionLat=carMarketPositionLatitude;
                userPositionLng=carMarketPositionLongitude;

                userLatLng = new google.maps.LatLng({lat: userPositionLat, lng: userPositionLng});
                checkUserPositionInMarketMap(userLatLng);

                infoWindow.setPosition(pos);
                infoWindow.setContent('you are here.');
                infoWindow.open(carMarketMap);
                carMarketMap.setCenter(pos);
            },
            function() {
                handleLocationError(true, infoWindow, carMarketMap.getCenter());
            }
        );

    }
    else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, carMarketMap.getCenter());
    }

}
/*------------------------------------------------------------------------------------------------------------*/
//NOT USED: helper function
function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
        'Error: The Geolocation service failed.' :
        'Error: Your browser doesn\'t support geolocation.');
    infoWindow.open(carMarketMap);
}
/*------------------------------------------------------------------------------------------------------------*/
/*------------------------------------------------------------------------------------------------------------*/
//function to display Car position
function displayCarPosition(carLatitude, carLongitude){
    var h3 = document.getElementById("carLocationText");
    if(carLatitude == 0 || carLongitude == 0){
        h3.style.color = "red";
        h3.innerHTML = "car location is not set. please check in your car.";
        return;
    }
    var carLatLng;
    var carInfoWindow = new google.maps.InfoWindow;
    var carPosition = {
        lat: carLatitude,
        lng: carLongitude
    };
    carLatLng = new google.maps.LatLng({lat: carLatitude, lng: carLongitude});
    checkUserPositionInMarketMap(carLatLng);
    carInfoWindow.setPosition(carPosition);
    //carInfoWindow.setContent('the car is here.');
    carInfoWindow.setContent('<img src="http://www.car-logos.org/wp-content/uploads/2011/09/dacia.png" alt="car logo" width="25" height="25">');
    carInfoWindow.open(carMarketMap);
    //carMarketMap.setCenter(carPosition);
    h3.innerHTML = "Location of the car on map. Click here to display route to the car.";
    $('#carLocationText').one('click',function(){
        displayRoute(carLatitude,carLongitude);
        carInfoWindow.setContent("car: (" + Math.round(distanceOfRoute)+")m");
    })
}
/*------------------------------------------------------------------------------------------------------------*/

/*------------------------------------------------------------------------------------------------------------*/
//NOT USED: function to generateRandomLocation.
function generateRandomLocation(){
    //car market boundaries;
    var NWLat = 46.750770;
    var NWLng = 23.425043;

    var SWLat = 46.749651;
    var SWLng = 23.424609;

    var NELat = 46.750696;
    var NELng = 23.430960;

    var SELat = 46.749349;
    var SELng = 23.430949;

    //random latitude and longitude in the market boundaries
    var safeWestLongitude = Math.max(NWLng,SWLng);
    var safeEastLongitude = Math.min(NELng,SELng);
    var randomLongitude = safeWestLongitude + (Math.random() * (safeEastLongitude - safeWestLongitude));

    if (randomLongitude > safeEastLongitude){
        randomLongitude = safeEastLongitude;
    }
    if (randomLongitude < safeWestLongitude){
        randomLongitude = safeWestLongitude;
    }

    var safeNorthLatitude = Math.min(NWLat,NELat);
    var safeSouthLatitude = Math.max(SWLat,SELat);

    var randomLatitude = safeSouthLatitude + (Math.random() * (safeNorthLatitude - safeSouthLatitude));

    if (randomLatitude > safeNorthLatitude){
        randomLatitude = safeNorthLatitude;
    }
    if (randomLatitude < safeSouthLatitude){
        randomLatitude = safeSouthLatitude;
    }

    var result = new google.maps.LatLng(randomLatitude, randomLongitude);
    return result;
}
/*------------------------------------------------------------------------------------------------------------*/


//function to display a route between center of Market Map and user position (must be previously set)
/*------------------------------------------------------------------------------------------------------------*/
function displayRoute(carLatitude, carLongitude) {
    var userPositionLatLng = generateRandomLocation();
    userPositionLat = userPositionLatLng.lat();
    userPositionLng = userPositionLatLng.lng();

    var userInfoWindow = new google.maps.InfoWindow;
    var userPosition = {
        lat: userPositionLat,
        lng: userPositionLng
    };
    checkUserPositionInMarketMap(userPositionLatLng);
    userInfoWindow.setPosition(userPosition);
    userInfoWindow.setContent('you');
    //carInfoWindow.setContent('<img src="http://www.car-logos.org/wp-content/uploads/2011/09/dacia.png" alt="car logo" width="25" height="25">');
    userInfoWindow.open(carMarketMap);
    //carMarketMap.setCenter(carPosition);

    var flightPlanCoordinates = [
        {lat: carLatitude, lng: carLongitude},
        {lat: userPositionLat, lng: userPositionLng}];
    var flightPath = new google.maps.Polyline({
        path: flightPlanCoordinates,
        geodesic: true,
        strokeColor: 'black',
        strokeOpacity: 1.0,
        strokeWeight: 2
    });
    flightPath.setMap(carMarketMap);
    distanceOfRoute = google.maps.geometry.spherical.computeLength(flightPath.getPath());
    fitToBoundariesForRoute(carMarketMap,carLatitude,carLongitude,userPositionLat,userPositionLng);
}
/*------------------------------------------------------------------------------------------------------------*/

/*------------------------------------------------------------------------------------------------------------*/
//function to validate if the user position is within the boundaries of the car market map
function checkUserPositionInMarketMap(positionToCheckLatLng) {
    //boundaries of the Car Market Map
    var NWLatLng = new google.maps.LatLng(46.750770, 23.425043);
    var SWLatLng = new google.maps.LatLng(46.749651, 23.424609);
    var NELatLng = new google.maps.LatLng(46.750696, 23.430960);
    var SELatLng = new google.maps.LatLng(46.749349, 23.430949);
    var carMarketPolygonPaths = [
        {lat: NWLatLng.lat(), lng: NWLatLng.lng()},
        {lat: SWLatLng.lat(), lng: SWLatLng.lng()},
        {lat: SELatLng.lat(), lng: SELatLng.lng()},
        {lat: NELatLng.lat(), lng: NELatLng.lng()}
    ];
    var carMarketPolygon = new google.maps.Polygon({paths: carMarketPolygonPaths});
    var result = google.maps.geometry.poly.containsLocation(positionToCheckLatLng, carMarketPolygon);
    if(!result){
        alert("OUTSIDE of the market area!");
    }
}
/*------------------------------------------------------------------------------------------------------------*/

/*------------------------------------------------------------------------------------------------------------*/
//function to fit the view to boundaries to include the route in the view
function fitToBoundariesForRoute(map, carLatitude, carLongitude, userLatitude, userLongitude){
    //boundaries of the Car Market Map
    var carLatLng = new google.maps.LatLng(carLatitude, carLongitude);
    var userLatLng = new google.maps.LatLng(userLatitude, userLongitude);

    //car marker
    var markerCar = new google.maps.Marker({position: carLatLng,map: map,title: 'Car Marker!'});
    markerCar.setVisible(false);
    //user marker
    var markerUser = new google.maps.Marker({position: userLatLng,map: map,title: 'User Marker!'});
    markerUser.setVisible(false);

    var markers = [markerCar,markerUser];
    var newBoundary = new google.maps.LatLngBounds();

    for (var i = 0; i < markers.length; i++) {
        var position = markers[i].getPosition();
        newBoundary.extend(position);
    }
    map.fitBounds(newBoundary);

}
/*------------------------------------------------------------------------------------------------------------*/






