package idroid.android.locationkit.factory

import android.app.Activity
import android.os.Looper
import android.util.Log
import com.huawei.hms.location.*
import idroid.android.locationkit.listener.LocationListener


class HuaweiLocationImpl(activity: Activity) : BaseLocation(activity) {
    private var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    init {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
    }

    override fun getLastKnownLocation(locationListener: LocationListener) {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            if (location == null) {
                Log.i("Location Kit", "Huawei Service Get Last Known Location is NULL..")
                return@addOnSuccessListener
            }
            locationListener.onLocationUpdate(location)
        }.addOnFailureListener { err ->
            Log.i(
                "Location Kit",
                "Huawei Services Last Know Location is fail Cause : ${err.message}"
            )
        }
    }

    override fun requestLocationUpdates(locationListener: LocationListener) {
        val locationRequest = LocationRequest()
        locationRequest.interval = 100000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        if (locationCallback == null) {
            locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    if (locationResult != null) {
                        locationListener.onLocationUpdate(locationResult.lastLocation)
                    }
                }
            }
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest, locationCallback,
                Looper.getMainLooper()
            ).addOnSuccessListener {
                Log.i("Location Kit", "Huawei Request Location Updated..")
            }.addOnFailureListener { err ->
                Log.i("Location Kit", "Huawei Request Location Fail Cause : ${err.message}")
            }
        } else {
            Log.i("Location Kit", "Already Request Updated.")
        }
    }

    override fun removeLocationUpdates() {
        if (locationCallback == null) return
        else {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
                .addOnSuccessListener {
                    Log.i("Location Kit", "Huawei Service Remove Location..")
                }.addOnFailureListener { err ->
                    Log.i(
                        "Location Kit",
                        "Huawei Service Remove Location Fail Cause : ${err.message}"
                    )
                }
        }
    }
}