package idroid.android.locationkit.manager

import android.app.Activity
import android.util.Log
import androidx.core.app.ActivityCompat
import idroid.android.locationkit.constants.Constants
import idroid.android.locationkit.constants.Constants.TAG
import idroid.android.locationkit.factory.Location
import idroid.android.locationkit.factory.LocationFactory
import idroid.android.locationkit.listener.LocationListener
import idroid.android.locationkit.utils.CheckServiceAvaiable
import idroid.android.locationkit.utils.Priority
import idroid.android.locationkit.utils.Utils

class HuaweiGoogleLocationManager(private val activity: Activity) {
    private var location: Location? = null

    init {
        location = LocationFactory.getLocationFactory(
            activity,
            CheckServiceAvaiable.getAvailableService(activity)
        )
    }

    fun requestLocationPermission() {
        if (Utils.checkPermission(activity)) Log.i(TAG, Constants.Permission.PERMISSION_GRANTED)
        else {
            val strings = if (Utils.isBuildVersionDownToP()) arrayOf(
                Constants.Permission.COARSE_LOCATION,
                Constants.Permission.FINE_LOCATION
            ) else arrayOf(
                Constants.Permission.COARSE_LOCATION,
                Constants.Permission.FINE_LOCATION,
                Constants.Permission.BACKGROUND_LOCATION
            )
            ActivityCompat.requestPermissions(activity, strings, 2)
        }
    }

    fun isLocationPermissionGranted(): Boolean = Utils.checkPermission(activity)

    fun requestLocationUpdates(
        locationListener: LocationListener,
        priority: Priority? = null,
        interval: Long? = null
    ) {
        location?.requestLocationUpdates(locationListener, priority, interval)
    }

    fun removeLocationUpdates() {
        location?.removeLocationUpdates()
    }

    fun getLastKnownLocation(locationListener: LocationListener) {
        location?.getLastKnownLocation(locationListener)
    }
}