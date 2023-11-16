package tn.esprit.safeguardapplication.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import tn.esprit.safeguardapplication.repository.UserRepository

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {

    companion object {
        private const val TAG = "SignUpViewModel"
    }

    fun signUp(email: String, password: String, fullName: String, phoneNumber: Int) = liveData(Dispatchers.IO) {
        try {
            // Call the signUp function in the repository
            val response = userRepository.signUp(email, password, fullName, phoneNumber)

            // Check if the response is successful
            if (response != null) {
                emit(response)
            } else {
                Log.e(TAG, "Unsuccessful response or null response")
                // You might want to emit a specific error state or handle it accordingly
                emit(null)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception: ${e.message}")
            emit(null)
        }
    }

    // Factory to create instances of SignUpViewModel
    class Factory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SignUpViewModel(userRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
