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
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val info = findViewById<ImageView>(R.id.nav_information)

        info.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(this@MainActivity, InformationFragment::class.java)
                startActivity(intent)

            }
        })


    }

}