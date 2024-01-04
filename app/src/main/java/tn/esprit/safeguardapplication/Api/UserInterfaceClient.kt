package tn.esprit.safeguardapplication.Api

import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.safeguardapplication.models.User

class UserInterfaceClient {
   private val BASE_URL = "http://192.168.1.54:9090/user/"

    private val TAG: String = "CHECK_RESPONSE"

    fun getUserApiService(): UserApiService {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return api.create(UserApiService::class.java)
    }

    fun signIn(email: String, password: String) {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userApiService = api.create(UserApiService::class.java)

        // Assuming signIn function in UserApiService requires a RequestBody
        val requestBody = RequestBody.create(
            "application/json".toMediaTypeOrNull(),
            "{\"email\":\"$email\", \"password\":\"$password\"}"
        )

        userApiService.signIn(requestBody).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let {
                        Log.i(TAG, "onResponse: ${it.UserName}")
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
            }
        })
    }
}
