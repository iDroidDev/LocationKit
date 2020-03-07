package idroid.android.samplelocationkit

import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import idroid.android.locationkit.listener.LocationListener
import idroid.android.locationkit.manager.HuaweiGoogleLocationManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var locationService: HuaweiGoogleLocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationService = HuaweiGoogleLocationManager(this)

        btnCurrentLocation.setOnClickListener {
            locationService.requestLocationUpdates(object : LocationListener {
                override fun onLocationUpdate(currentLocation: Location) {
                    Toast.makeText(
                        this@MainActivity,
                        "Current Location Latitude: ${currentLocation.latitude} ,Current Location Longitude: ${currentLocation.longitude}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }

        btnLastKnownLocation.setOnClickListener {
            locationService.getLastKnownLocation(object : LocationListener {
                override fun onLocationUpdate(lastKnownLocation: Location) {
                    Toast.makeText(
                        this@MainActivity,
                        "Last Known Location Latitude: ${lastKnownLocation.latitude} ,Current Location Longitude: ${lastKnownLocation.longitude}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }

    }
}
