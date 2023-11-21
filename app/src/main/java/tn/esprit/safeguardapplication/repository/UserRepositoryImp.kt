package tn.esprit.safeguardapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import tn.esprit.safeguardapplication.Api.UserApiService
import tn.esprit.safeguardapplication.models.User

class UserRepositoryImpl(private val userService: UserApiService) : UserRepository {

    override suspend fun signIn(email: String, password: String): MutableLiveData<User?> {
        return withContext(Dispatchers.IO) {
            val resultLiveData = MutableLiveData<User?>()

            try {
                val requestBody = RequestBody.create(
                    "application/json".toMediaTypeOrNull(),
                    "{\"email\":\"$email\", \"password\":\"$password\"}"
                )

                val response = userService.signIn(requestBody).execute()

                if (response.isSuccessful) {
                    val user = response.body()
                    resultLiveData.postValue(user)
                } else {
                    resultLiveData.postValue(null)
                }
            } catch (e: Exception) {
                resultLiveData.postValue(null)
            }

            resultLiveData
        }
    }

    override suspend fun signUp(
        UserName: String,
        email: String,
        password: String,
        phoneNumber: Int
    ): LiveData<User?> {
        return withContext(Dispatchers.IO) {
            val resultLiveData = MutableLiveData<User?>()

            try {
                val requestBody = RequestBody.create(
                    "application/json".toMediaTypeOrNull(),
                    "{\"UserName\":\"$UserName\", \"email\":\"$email\", \"password\":\"$password\", \"phoneNumber\":$phoneNumber}"
                    // Removed unnecessary quotes around $phoneNumber
                )

                val response = userService.signUp(requestBody).execute()

                if (response.isSuccessful) {
                    val user = response.body()
                    resultLiveData.postValue(user)
                } else {
                    resultLiveData.postValue(null)
                }
            } catch (e: Exception) {
                resultLiveData.postValue(null)
            }

            resultLiveData
        }
    }

    override suspend fun displayUserProfile(
        UserName: String,
        email: String,
        password: String,
        phoneNumber: Int
    ): LiveData<User?> {
        TODO("Not yet implemented")
    }


    override suspend fun displayUserProfile(userId: String): LiveData<User?> {
        return withContext(Dispatchers.IO) {
            val resultLiveData = MutableLiveData<User?>()

            try {
                // Modify the API endpoint and parameters according to your API
                val response = userService.displayUserProfile(userId).execute()

                if (response.isSuccessful) {
                    val user = response.body()
                    resultLiveData.postValue(user)
                } else {
                    resultLiveData.postValue(null)
                }
            } catch (e: Exception) {
                resultLiveData.postValue(null)
            }

            resultLiveData
        }
    }



}
