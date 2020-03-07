package idroid.android.locationkit.listener

import android.location.Location

interface LocationListener {
    fun onLocationUpdate(location: Location)
}