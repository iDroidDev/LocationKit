package idroid.android.locationkit.factory

import android.app.Activity
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.*
import idroid.android.locationkit.constants.Constants
import idroid.android.locationkit.listener.LocationListener
import idroid.android.locationkit.utils.Priority

class GoogleLocationImpl(activity: Activity) : BaseLocation(activity) {

    private var fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(activity)
    private var locationCallback: LocationCallback? = null

    override fun getLastKnownLocation(locationListener: LocationListener) {
        super.getLastKnownLocation(locationListener)

        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            if (location == null) {
                Log.i(Constants.TAG, Constants.LocationWarningMessage.LAST_KNOW_LOCATION_NULL)
                return@addOnSuccessListener
            }
            locationListener.onLocationUpdate(location)
        }.addOnFailureListener { err ->
            Log.i(
                Constants.TAG,
                Constants.LocationWarningMessage.LAST_KNOW_LOCATION_FAIL + err.message
            )
        }
    }

    override fun requestLocationUpdates(
        locationListener: LocationListener,
        priority: Priority?,
        interval: Long?
    ) {
        super.requestLocationUpdates(locationListener, priority, interval)

        val locationRequest = LocationRequest.create()
        locationRequest.interval = interval ?: 100000
        locationRequest.priority = when (priority) {
            Priority.PRIORITY_BALANCED_POWER_ACCURACY -> LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
            Priority.PRIORITY_HIGH_ACCURACY -> LocationRequest.PRIORITY_HIGH_ACCURACY
            Priority.PRIORITY_LOW_POWER -> LocationRequest.PRIORITY_LOW_POWER
            Priority.PRIORITY_NO_POWER -> LocationRequest.PRIORITY_NO_POWER
            else -> LocationRequest.PRIORITY_HIGH_ACCURACY
        }

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
                Log.i(Constants.TAG, Constants.LocationWarningMessage.CURRENT_LOCATION_SUCCESS)
            }.addOnFailureListener { err ->
                Log.i(
                    Constants.TAG,
                    Constants.LocationWarningMessage.CURRENT_LOCATION_FAIL + err.message
                )
            }
        } else Log.i(Constants.TAG, Constants.LocationWarningMessage.CURRENT_LOCATION_UPDATED)
    }

    override fun removeLocationUpdates() {
        super.removeLocationUpdates()

        if (locationCallback == null) Log.i(
            Constants.TAG,
            Constants.LocationWarningMessage.CURRENT_LOCATION_REMOVE_NULL
        )
        else {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
                .addOnSuccessListener {
                    locationCallback = null
                    Log.i(
                        Constants.TAG,
                        Constants.LocationWarningMessage.CURRENT_LOCATION_REMOVE_SUCCESS
                    )
                }.addOnFailureListener { err ->
                    Log.i(
                        Constants.TAG,
                        Constants.LocationWarningMessage.CURRENT_LOCATION_REMOVE_FAIL + err.message
                    )
                }
        }
    }

}