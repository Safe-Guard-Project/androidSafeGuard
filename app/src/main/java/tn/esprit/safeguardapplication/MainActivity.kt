package tn.esprit.safeguardapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.safeguardapplication.UI.Activities.MapActivity
import tn.esprit.safeguardapplication.databinding.ActivityMainBinding
import tn.esprit.safeguardapplication.util.LocationHelper


class MainActivity : AppCompatActivity() {
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


    }
    override fun onDestroy() {
        super.onDestroy()
        locationHelper.disableLocationUpdates()
    }
    }
