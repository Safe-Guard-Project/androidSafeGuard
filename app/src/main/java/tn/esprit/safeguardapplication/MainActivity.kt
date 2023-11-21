package tn.esprit.safeguardapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tn.esprit.safeguardapplication.UI.Activities.ui.FeedBack
import tn.esprit.safeguardapplication.UI.Activities.ui.ProgrammeActivity
import tn.esprit.safeguardapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonProg.setOnClickListener() {
            val intent = Intent(this, ProgrammeActivity::class.java)
            startActivity(intent)
        }
        binding.buttonFeedback.setOnClickListener() {
            val intent = Intent(this, FeedBack::class.java)
            startActivity(intent)
        }

    }
}