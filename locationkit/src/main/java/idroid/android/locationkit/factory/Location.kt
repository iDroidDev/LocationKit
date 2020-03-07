package idroid.android.locationkit.factory

import idroid.android.locationkit.listener.LocationListener

interface Location {
    fun getLastKnownLocation(locationListener: LocationListener)
    fun requestLocationUpdates(locationListener: LocationListener)
    fun removeLocationUpdates()
}