package tn.esprit.safeguardapplication.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import tn.esprit.safeguardapplication.MainActivity
import tn.esprit.safeguardapplication.databinding.ActivitySignInBinding
import tn.esprit.safeguardapplication.repository.RetrofitInstance
import tn.esprit.safeguardapplication.repository.UserRepository
import tn.esprit.safeguardapplication.repository.UserRepositoryImpl
import tn.esprit.safeguardapplication.viewmodels.SignInViewModel
import tn.esprit.safeguardapplication.api.UserApiService
const val SIGN_IN_TAG = "SignIn Activity"

class SignInActivity : ComponentActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switchLoginSignupButton=binding.switchLoginSignupButton
        switchLoginSignupButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        // Create an instance of the UserRepository implementation
        val userRepository: UserRepository = UserRepositoryImpl(RetrofitInstance.api)


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

            viewModel.signIn(email, password).observe(this@SignInActivity) { response ->
                if (response != null) {
                    // Sign-in successful, navigate to the next screen or perform other actions
                    val intent = Intent(this@SignInActivity, DisplayUserProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Sign-in failed, handle the error
                    Log.d("SignInActivity", "error")
                }
            }
        }


    }
}
