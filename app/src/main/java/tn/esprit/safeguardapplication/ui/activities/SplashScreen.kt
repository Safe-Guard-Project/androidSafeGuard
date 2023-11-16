package tn.esprit.safeguardapplication.ui.activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import tn.esprit.safeguardapplication.MainActivity
import tn.esprit.safeguardapplication.R
import tn.esprit.safeguardapplication.ui.activities.SignInActivity
class SplashScreen : AppCompatActivity() {
    private val delay : Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen) // Set the content view to SignInActivity layout


        // Simulate a delay (e.g., 2 seconds) for the splash screen
        Handler().postDelayed({
            val   intent = Intent(this ,SignInActivity::class.java)
            startActivity(intent)
            finish()
        },delay)
    }
}