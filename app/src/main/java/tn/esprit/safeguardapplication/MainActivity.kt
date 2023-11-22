package tn.esprit.safeguardapplication


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.safeguardapplication.UI.Activities.MapActivity
import tn.esprit.safeguardapplication.databinding.ActivityMainBinding
import tn.esprit.safeguardapplication.util.LocationHelper

const val TAGI = "Main Activity"

class MainActivity : ComponentActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var locationHelper: LocationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationHelper = LocationHelper(this)
        locationHelper.onLocationUpdated = { latitude, longitude ->
            Log.e("Location", "Latitude: $latitude ; Longitude: $longitude")
        }

        val mapImageView = findViewById<ImageView>(R.id.nav_map)
        val homeImageView = findViewById<ImageView>(R.id.nav_home)
        mapImageView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(this@MainActivity, MapActivity::class.java)
                startActivity(intent)
            }
        })
        homeImageView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(this@MainActivity, MainActivity::class.java)
                startActivity(intent)
            }
        })

      
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channel_id",
                "notification_channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }

        FirebaseMessaging.getInstance().subscribeToTopic("general")
            .addOnCompleteListener { task ->
                var msg = "Subscribed Successfully"
                if (!task.isSuccessful) {
                    msg = "Subscription failed"
                }
                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            }
    }
   override fun onDestroy() {
        super.onDestroy()
        locationHelper.disableLocationUpdates()
    }
  
}




