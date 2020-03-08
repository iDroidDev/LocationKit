package idroid.android.locationkit.factory

import android.content.Context
import android.util.Log
import idroid.android.locationkit.constants.Constants
import idroid.android.locationkit.listener.LocationListener
import idroid.android.locationkit.utils.Priority
import idroid.android.locationkit.utils.Utils

abstract class BaseLocation(private val context: Context) : Location {

    override fun getLastKnownLocation(locationListener: LocationListener) {
        if (Utils.checkPermission(context)) Log.e(
            Constants.TAG,
            Constants.Permission.PERMISSION_NEED
        )
    }

    override fun requestLocationUpdates(
        locationListener: LocationListener,
        priority: Priority?,
        interval: Long?
    ) {
        if (Utils.checkPermission(context)) Log.e(
            Constants.TAG,
            Constants.Permission.PERMISSION_NEED
        )
    }

    override fun removeLocationUpdates() {
        if (Utils.checkPermission(context)) Log.e(
            Constants.TAG,
            Constants.Permission.PERMISSION_NEED
        )
    }
}