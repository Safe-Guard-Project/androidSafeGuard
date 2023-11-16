package tn.esprit.safeguardapplication.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import tn.esprit.safeguardapplication.models.User
import tn.esprit.safeguardapplication.repository.UserRepository

class DisplayUserProfileViewModel(private val userRepository: UserRepository) : ViewModel() {

    companion object {
        private const val TAG = "DisplayUserProfileViewModel"
    }

    fun displayUserProfile(userId: String) = liveData(Dispatchers.IO) {
        try {
            // Call the displayUserProfile function in the repository
            val user = userRepository.displayUserProfile(userId)

            // Check if the user is not null
            if (user != null) {
                emit(user)
            } else {
                Log.e(TAG, "User is null")
                // You might want to emit a specific error state or handle it accordingly
                emit(null)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception: ${e.message}")
            emit(null)
        }
    }


    // Factory to create instances of DisplayUserProfileViewModel
    class Factory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DisplayUserProfileViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DisplayUserProfileViewModel(userRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
