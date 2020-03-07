package idroid.android.locationkit.manager

import android.app.Activity
import androidx.core.app.ActivityCompat
import idroid.android.locationkit.constants.PermissionConstants
import idroid.android.locationkit.factory.Location
import idroid.android.locationkit.factory.LocationFactory
import idroid.android.locationkit.listener.LocationListener
import idroid.android.locationkit.utils.CheckServiceAvaiable
import idroid.android.locationkit.utils.Utils

class HuaweiGoogleLocationManager(activity: Activity) {
    private var location: Location? = null

    init {
        if (Utils.checkPermission(activity)) {
            location = LocationFactory.getLocationFactory(
                activity,
                CheckServiceAvaiable.getAvailableService(activity)
            )
        } else {
            val strings = arrayOf(
                PermissionConstants.COARSE_LOCATION,
                PermissionConstants.FINE_LOCATION
            )
            ActivityCompat.requestPermissions(activity, strings, 2)
        }
    }

    fun requestLocationUpdates(locationListener: LocationListener) {
        location?.requestLocationUpdates(locationListener)
    }


    fun removeLocationUpdates() {
        location?.removeLocationUpdates()
    }

    fun getLastKnownLocation(locationListener: LocationListener) {
        location?.getLastKnownLocation(locationListener)
    }
}