package tn.esprit.safeguardapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.safeguardapplication.UI.Fragments.InformationFragment
import tn.esprit.safeguardapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val info = findViewById<ImageView>(R.id.nav_information)

        val firstFragment= InformationFragment()
        info.setOnClickListener(){
            supportFragmentManager.beginTransaction().replace(R.id.inf , firstFragment).commit()

        }

    }

}