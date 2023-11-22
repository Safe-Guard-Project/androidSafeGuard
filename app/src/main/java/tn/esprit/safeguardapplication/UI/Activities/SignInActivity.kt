package tn.esprit.safeguardapplication.UI.Activities


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import tn.esprit.safeguardapplication.MainActivity
import tn.esprit.safeguardapplication.databinding.ActivitySignInBinding
import tn.esprit.safeguardapplication.repository.RetrofitInstance
import tn.esprit.safeguardapplication.repository.UserRepositoryImpl
import tn.esprit.safeguardapplication.util.SharedPreferencesUtils
import tn.esprit.safeguardapplication.viewmodels.SignInViewModel


const val SIGN_IN_TAG = "SignIn Activity"


class SignInActivity : ComponentActivity() {


    private lateinit var binding: ActivitySignInBinding
    private lateinit var viewModel: SignInViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val switchLoginSignupButton = binding.switchLoginSignupButton
        switchLoginSignupButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


        // Create an instance of the UserRepository implementation
        val userRepository = UserRepositoryImpl(RetrofitInstance.apiUser)


        // Create a SignInViewModel.Factory with the UserRepository instance
        val signInViewModelFactory = SignInViewModel.Factory(userRepository)


        // Use the factory to create an instance of SignInViewModel
        viewModel = ViewModelProvider(this, signInViewModelFactory).get(SignInViewModel::class.java)


        observeSignIn()
    }


    private fun observeSignIn() {
        Log.d(SIGN_IN_TAG, "observeSignIn: Button Clicked!")


        binding.SigninB.setOnClickListener {
            val email = binding.emailsi.text.toString()
            val password = binding.passwordsi.text.toString()


            Toast.makeText(this@SignInActivity, "Button Clicked!", Toast.LENGTH_SHORT).show()


            lifecycleScope.launch {
                try {
                    viewModel.signIn(email, password)


                    viewModel.signInResult.observe(this@SignInActivity) { user ->
                        user?.let {
                            // Extract user ID from the JWT token
                            val userId = user.extractUserIdFromToken()
                            Log.d("SignInActivity", "User ID extracted: $userId")


                            // Store user ID in SharedPreferences
                            val sharedPreferences = getSharedPreferences(
                                SharedPreferencesUtils.SHARED_PREFS_NAME,
                                MODE_PRIVATE
                            )
                            val editor = sharedPreferences.edit()
                            editor.putString("userId", userId)
                            editor.apply()
                            Log.d(
                                "SignInActivity",
                                "User ID stored in SharedPreferences: $userId"
                            )


                            val intent = Intent(
                                this@SignInActivity,
                                MainActivity::class.java
                            )
                            startActivity(intent)
                            finish()
                        }
                    }
                } catch (e: Exception) {
                    // Handle exceptions here
                    Log.e("SignInActivity", "Error during sign-in: ${e.message}", e)
                }
            }
        }
    }






    private fun extractUserIdFromToken(jwtToken: String): String {
        // JWT token consists of three parts: header, payload, and signature
        // They are separated by dots. We need the payload (middle part)
        val parts = jwtToken.split(".")


        // Decode the payload
        val payload = parts[1]
        val payloadJson =
            android.util.Base64.decode(payload, android.util.Base64.URL_SAFE)
                .toString(charset("UTF-8"))


        // Parse the JSON payload and extract the user ID
        val jsonObject = JSONObject(payloadJson)
        return jsonObject.getString("_id")
    }
}
