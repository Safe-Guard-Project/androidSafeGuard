package tn.esprit.safeguardapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tn.esprit.safeguardapplication.R.*
import tn.esprit.safeguardapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(layout.activity_main)

        binding.info.setOnClickListener() {
            val intent = Intent(this, InformationFragment::class.java)
            startActivity(intent)
        }
    }
}