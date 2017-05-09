function generateRandomCarLocation(){
    //car market boundaries;
    var NWLat = 46.750770;
    var NWLng = 23.425043;

    var SWLat = 46.749651;
    var SWLng = 23.424609;

    var NELat = 46.750696;
    var NELng = 23.430960;

    var SELat = 46.749349;
    var SELng = 23.430949;

    //random car latitude and longitude in the market boundaries
    var safeWestLongitude = Math.max(NWLng,SWLng);
    var safeEastLongitude = Math.min(NELng,SELng);
    var randomCarLongitude = safeWestLongitude + (Math.random() * (safeEastLongitude - safeWestLongitude));

    if (randomCarLongitude > safeEastLongitude){
        randomCarLongitude = safeEastLongitude;
    }
    if (randomCarLongitude < safeWestLongitude){
        randomCarLongitude = safeWestLongitude;
    }

    var safeNorthLatitude = Math.min(NWLat,NELat);
    var safeSouthLatitude = Math.max(SWLat,SELat);

    var randomCarLatitude = safeSouthLatitude + (Math.random() * (safeNorthLatitude - safeSouthLatitude));

    if (randomCarLatitude > safeNorthLatitude){
        randomCarLatitude = safeNorthLatitude;
    }
    if (randomCarLatitude < safeSouthLatitude){
        randomCarLatitude = safeSouthLatitude;
    }

    var result = new google.maps.LatLng(randomCarLatitude, randomCarLongitude);
    alert("latitude: " + result.lat() + " - " + " longitude: " + result.lng());
}

