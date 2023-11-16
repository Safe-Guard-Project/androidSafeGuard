package tn.esprit.safeguardapplication.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import tn.esprit.safeguardapplication.R
import tn.esprit.safeguardapplication.viewmodels.DisplayUserProfileViewModel
import tn.esprit.safeguardapplication.models.User
import tn.esprit.safeguardapplication.repository.Resource
import tn.esprit.safeguardapplication.repository.RetrofitInstance
import tn.esprit.safeguardapplication.repository.UserRepository
import tn.esprit.safeguardapplication.repository.UserRepositoryImpl

class DisplayUserProfileActivity : AppCompatActivity() {

    private lateinit var txtFullName: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtNumTel: TextView

    private val displayUserProfileViewModel: DisplayUserProfileViewModel by viewModels {
        val userRepository: UserRepository = UserRepositoryImpl(RetrofitInstance.api)

        DisplayUserProfileViewModel.Factory(userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_user_profile)

        txtFullName = findViewById(R.id.txtFullName)
        txtEmail = findViewById(R.id.txtEmail)
        txtNumTel = findViewById(R.id.txtNumTel)

        // Retrieve user profile data from the Intent
        val email = intent.getStringExtra("email")
        val fullName = intent.getStringExtra("fullName")
        val phoneNumber = intent.getStringExtra("phoneNumber")
        val userId = intent.getStringExtra("userId") // Assuming "userId" is the key for user ID

        // Set the user profile data in TextViews
        txtFullName.text = fullName
        txtEmail.text = email
        txtNumTel.text = phoneNumber


        // Fetch user profile data from the ViewModel
        if (userId != null) {
            displayUserProfile(userId)
        } else {
            Log.e("DisplayUserProfile", "User ID is null")
        }
    }

    private fun displayUserProfile(userId: String) {
        displayUserProfileViewModel.displayUserProfile(userId).observe(this, Observer { user ->

            if (user != null) {
                // Update UI with user profile data
                txtFullName.text = R.id.txtFullName.toString()
                txtEmail.text = R.id.txtEmail.toString()
                txtNumTel.text = R.id.numeroTel.toString()
            } else {
                // Handle null user profile
                Log.e("DisplayUserProfile", "User is null")
            }
        })
    }





}
