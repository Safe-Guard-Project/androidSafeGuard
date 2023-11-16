package tn.esprit.safeguardapplication.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import tn.esprit.safeguardapplication.MainActivity
import tn.esprit.safeguardapplication.databinding.ActivitySignUpBinding
import tn.esprit.safeguardapplication.repository.RetrofitInstance
import tn.esprit.safeguardapplication.repository.UserRepository
import tn.esprit.safeguardapplication.repository.UserRepositoryImpl
import tn.esprit.safeguardapplication.viewmodels.SignUpViewModel // Change the import to SignUpViewModel
import tn.esprit.safeguardapplication.api.UserApiService

const val SIGN_UP_TAG = "SignUp Activity"

class SignUpActivity : ComponentActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val switchSignupLoginButton = binding.switchSignupLoginButton
        switchSignupLoginButton.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        // Create an instance of the UserRepository implementation
        val userRepository: UserRepository = UserRepositoryImpl(RetrofitInstance.api)

        // Create a SignUpViewModel.Factory with the UserRepository instance
        val signUpViewModelFactory = SignUpViewModel.Factory(userRepository)

        // Use the factory to create an instance of SignUpViewModel
        viewModel = ViewModelProvider(this, signUpViewModelFactory).get(SignUpViewModel::class.java)

        observeSignUp()
    }

    private fun observeSignUp() {
        Log.d(SIGN_UP_TAG, "observeSignUp: Button Clicked!")

        binding.signupB.setOnClickListener {
            val UserName = binding.fullnamesu.text.toString()
            val email = binding.emailsu.text.toString()
            val password = binding.passwordsu.text.toString()
            val phoneNumber = binding.numeroTel.text.toString()

            Toast.makeText(this@SignUpActivity, "Button Clicked!", Toast.LENGTH_SHORT).show()

            // Call your SignUp API function using viewModel.signUp(fullName, email, password, phoneNumber)
            // Update the ViewModel and API accordingly for SignUp

            // Example:
            viewModel.signUp(UserName, email, password, phoneNumber.toInt()).observe(this@SignUpActivity) { response ->
                if (response != null) {
                    // SignUp successful, navigate to the next screen or perform other actions
                    val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // SignUp failed, handle the error
                    Log.d("SignUpActivity", "error")
                }
            }
        }

        // Add any other necessary UI interactions or validations for SignUp
    }
}
