package tn.esprit.safeguardapplication.Api
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import tn.esprit.safeguardapplication.models.User

interface UserApiService {

    @POST("user/loginClient")
    fun signIn(@Body requestBody: RequestBody): Call<User>
    @POST("user/registerClient")
    fun signUp(@Body requestBody: RequestBody): Call<User>
    @GET("user/profile/{id}")
    fun displayUserProfile(@Path("id") userId: String): Call<User>
 }
