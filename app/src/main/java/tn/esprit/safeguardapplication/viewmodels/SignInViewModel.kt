package tn.esprit.safeguardapplication.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import tn.esprit.safeguardapplication.models.User
import tn.esprit.safeguardapplication.repository.UserRepository


// SignInViewModel
class SignInViewModel(private val userRepository: UserRepository) : ViewModel() {


    private val _signInResult = MutableLiveData<User?>()


    val signInResult: LiveData<User?>
        get() = _signInResult


    suspend fun signIn(email: String, password: String) {
        val requestBody = RequestBody.create(
            "application/json".toMediaTypeOrNull(),
            "{\"email\":\"$email\", \"password\":\"$password\"}"
        )


        withContext(Dispatchers.IO) {
            try {
                val response = userRepository.signIn(email, password)
                withContext(Dispatchers.Main) {
                    _signInResult.postUserValue(response)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _signInResult.postUserValue(null)
                }
            }
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


fun MutableLiveData<User?>.postUserValue(response: MutableLiveData<User?>?) {
    this.postValue(response)
}


private fun <T> MutableLiveData<T>.postValue(response: MutableLiveData<User?>?) {


}
