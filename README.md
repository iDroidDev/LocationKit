# LocationKit SDK

This SDK includes Huawei Location and Google Location enhancements.Recently, with the release of Huawei App Gallery, many applications are performing location integration.

### Our main goal now aims to solve your location related problems. You will have completed both integrations using only our libraries.

## Installing

### Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
```

### Add the dependency to your project build.gradle file:
```
        implementation 'com.github.iDroidDev:LocationKit:1.0.0'
```

## Usage

### Configure Location in your class
```
    val locationService: HuaweiGoogleLocationManager = HuaweiGoogleLocationManager(this)
    
    // get location permission
    if (!locationService.isLocationPermissionGranted()) locationService.requestLocationPermission()
    
    // subscribe location updates
    locationService.requestLocationUpdates(object : LocationListener {
                override fun onLocationUpdate(currentLocation: Location) {
                    Toast.makeText(
                        this@MainActivity,
                        "Current Location Latitude: ${currentLocation.latitude} ,Current Location Longitude: ${currentLocation.longitude}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
      
      // unsubscribe location updates
      locationService.removeLocationUpdates()
      
      
      // get last known location
      locationService.getLastKnownLocation(object : LocationListener {
                override fun onLocationUpdate(lastKnownLocation: Location) {
                    Toast.makeText(
                        this@MainActivity,
                        "Last Known Location Latitude: ${lastKnownLocation.latitude} ,Current Location Longitude: ${lastKnownLocation.longitude}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    
```

### Set up Location updates
```
    locationService.requestLocationUpdates(object : LocationListener {
                override fun onLocationUpdate(currentLocation: Location) {
                    // location update listener
                }
            }, priority = Priority.PRIORITY_BALANCED_POWER_ACCURACY, interval = 10000)
```

#### Priority
Priority paramater sets the priority of the updates, which gives the LocationKit services location services a strong hint about which location sources to use. The following values are supported:
```
    Priority.PRIORITY_HIGH_ACCURACY
    Priority.PRIORITY_BALANCED_POWER_ACCURACY
    Priority.PRIORITY_LOW_POWER
    Priority.PRIORITY_NO_POWER
```

#### Interval
Interval parameter sets the rate in milliseconds at which your app prefers to receive location updates. Note that the location updates may be somewhat faster or slower than this rate to optimize for battery usage, or there may be no updates at all (if the device has no connectivity, for example). **Standart priority is _"Priority.PRIORITY_HIGH_ACCURACY"_**


### If subscribed location updates, don't forget this code block
```
    override fun onDestroy() {
        locationService.removeLocationUpdates()
        super.onDestroy()
    }
```

### If using our MapKit, you can use below code block for camera update
```
    mapView.onCreate(mapViewBundle)
    mapView.getMapAsync {
            locationService.getLastKnownLocation { location ->
                if (location == null) {
                    locationService.subscribewLocationUpdates { locationUpdate ->
                        it.moveCamera(locationUpdate.latitude.toFloat(), locationUpdate.longitude.toFloat(), 20f)
                        it.addMarker(
                            "Your Location",
                            "${locationUpdate.latitude} - ${locationUpdate.longitude}",
                            locationUpdate.latitude.toFloat(),
                            locationUpdate.longitude.toFloat()
                        )
                    }
                } else {
                    it.moveCamera(location.latitude.toFloat(), location.longitude.toFloat(), 20f)
                    it.addMarker(
                        "Your Location",
                        "${location.latitude} - ${location.longitude}",
                        location.latitude.toFloat(),
                        location.longitude.toFloat()
                    )
                }
            }
        }
```
**Our MapKit SDK link:** www.github.com/iDroidDev/MapKit


# Authors

* **Kaan KÜN** - *Mobile Application Developer*
* **Mahmut YETİŞİR** - *Mobile Application Developer*

## Don't worry
You can always contact us.
* **Kaan KÜN** Email : kaanforum4@gmail.com
* **Mahmut YETİŞİR** Email : mahmutyetisir03@gmail.com

