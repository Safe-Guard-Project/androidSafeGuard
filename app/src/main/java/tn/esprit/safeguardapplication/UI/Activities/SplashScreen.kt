package tn.esprit.safeguardapplication.UI.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.safeguardapplication.MainActivity
import tn.esprit.safeguardapplication.R

class SplashScreen : AppCompatActivity() {
    private val delay : Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)



        Handler().postDelayed({
            val   intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
            finish()
        },delay)
    }
}