package tn.esprit.safeguardapplication.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import tn.esprit.safeguardapplication.repository.UserRepository

class SignInViewModel(private val userRepository: UserRepository) : ViewModel() {

    companion object {
        private const val TAG = "SignInViewModel"
    }

    fun signIn(email: String, password: String) = liveData(Dispatchers.IO) {
        try {
            // Call the signIn function in the repository
            val response = userRepository.signIn(email, password)

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

    // Factory to create instances of SignInViewModel
    class Factory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SignInViewModel(userRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
