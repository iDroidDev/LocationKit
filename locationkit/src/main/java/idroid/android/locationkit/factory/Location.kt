package idroid.android.locationkit.factory

import idroid.android.locationkit.listener.LocationListener
import idroid.android.locationkit.utils.Priority

interface Location {
    fun getLastKnownLocation(locationListener: LocationListener)
    fun requestLocationUpdates(
        locationListener: LocationListener,
        priority: Priority?,
        interval: Long?
    )
    fun removeLocationUpdates()
}