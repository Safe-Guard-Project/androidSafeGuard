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
        private val interceptor = HttpLoggingInterceptor().apply {
        level =HttpLoggingInterceptor.Level.BODY}
        private  val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()


        val api: ProgrammeApi by lazy {

            Retrofit.Builder()
                .baseUrl("http://192.168.7.131:9090/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProgrammeApi::class.java)
        }
        val commentApi: CommentApi by lazy {


            Retrofit.Builder()
                .baseUrl("http://192.168.7.131:9090/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CommentApi::class.java)
        }
        val favApi: FavApi by lazy {


            Retrofit.Builder()
                .baseUrl("http://192.168.7.131:9090/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FavApi::class.java)
        }

        val feedBApi :FeedBApi by lazy {
            Retrofit.Builder()
                .baseUrl("http://192.168.7.131:9090/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FeedBApi::class.java)
        }




    }
}

