package tn.esprit.safeguardapplication.api
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import tn.esprit.safeguardapplication.models.User

interface UserApiService {

    @POST("loginClient")
    fun signIn(@Body requestBody: RequestBody): Call<User>
    @POST("registerClient")
    fun signUp(@Body requestBody: RequestBody): Call<User>
    @GET("profile/{id}")
    fun displayUserProfile(@Path("id") userId: String): Call<User>
 }
