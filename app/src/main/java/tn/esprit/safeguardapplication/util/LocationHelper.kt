package tn.esprit.safeguardapplication.util

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle

class LocationHelper(private val context: Context) {

    private var locationManager: LocationManager? = null
    var userLatitude: Double? = null
    var userLongitude: Double? = null
    var onLocationUpdated: ((Double, Double) -> Unit)? = null

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            userLatitude = location.latitude
            userLongitude = location.longitude
            onLocationUpdated?.invoke(userLatitude!!, userLongitude!!)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    init {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        try {
            // Request location updates
            locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, locationListener)
        } catch(ex: SecurityException) {
            // Handle case where location permissions are not granted
        }
    }

    fun disableLocationUpdates() {
        locationManager?.removeUpdates(locationListener)
    }
}
