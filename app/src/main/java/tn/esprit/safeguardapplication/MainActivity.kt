package tn.esprit.safeguardapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tn.esprit.safeguardapplication.UI.Activities.ui.FeedBack
import tn.esprit.safeguardapplication.UI.Activities.ui.Listfav
import tn.esprit.safeguardapplication.UI.Fragments.ProgrammeFragment

import tn.esprit.safeguardapplication.databinding.ActivityMainBinding
import tn.esprit.safeguardapplication.databinding.FragmentProgrammeBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val progFragment=ProgrammeFragment()
        binding.buttonProg.setOnClickListener() {
            supportFragmentManager.beginTransaction().replace(R.id.container , progFragment).commit()
        }
        binding.buttonFeedback.setOnClickListener() {
            val intent = Intent(this, FeedBack::class.java)
            startActivity(intent)
        }
        binding.buttonFav.setOnClickListener() {
            val intent = Intent(this, Listfav::class.java)
            startActivity(intent)
        }

    }
}