package tn.esprit.safeguardapplication.Api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.safeguardapplication.models.Programme

class RetrofitImpl {

    companion object {


        val api: ProgrammeApi by lazy {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            Retrofit.Builder()
                .baseUrl("http://192.168.82.131:9090/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProgrammeApi::class.java)
        }
        val commentApi: CommentApi by lazy {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            Retrofit.Builder()
                .baseUrl("http://192.168.82.131:9090/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CommentApi::class.java)
        }
        val favApi: FavApi by lazy {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            Retrofit.Builder()
                .baseUrl("http://192.168.82.131:9090/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FavApi::class.java)
        }





    }
}

