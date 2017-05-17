
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
function calculateDistance(carLatitude, carLongitude) {
    var element = document.getElementById('distance');
    if (carLatitude == null || carLongitude == null || carLatitude == 0 || carLongitude ==0) {
        element.innerHTML = "N/A";
        return;
    }
    var userPositionLatLng = generateRandomLocation();
    userPositionLat = userPositionLatLng.lat();
    userPositionLng = userPositionLatLng.lng();
    //var userInfoWindow = new google.maps.InfoWindow;
    var userPosition = {
        lat: userPositionLat,
        lng: userPositionLng
    };
    checkUserPositionInMarketMap(userPositionLatLng);
    //userInfoWindow.setPosition(userPosition);
    //userInfoWindow.setContent('you');
    //carInfoWindow.setContent('<img src="http://www.car-logos.org/wp-content/uploads/2011/09/dacia.png" alt="car logo" width="25" height="25">');
    //userInfoWindow.open(carMarketMap);
    //carMarketMap.setCenter(carPosition);

    var flightPlanCoordinates = [
        {lat: carLatitude, lng: carLongitude},
        {lat: userPositionLat, lng: userPositionLng}];
    var flightPath = new google.maps.Polyline({
        path: flightPlanCoordinates,
        geodesic: true,
        strokeColor: 'black',
        strokeOpacity: 1.0,
        strokeWeight: 0
    });
    //flightPath.setMap(carMarketMap);
    distanceOfRoute = google.maps.geometry.spherical.computeLength(flightPath.getPath());
    element.innerHTML = Math.round(distanceOfRoute) + " m";
    //fitToBoundariesForRoute(carMarketMap,carLatitude,carLongitude,userPositionLat,userPositionLng);
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




