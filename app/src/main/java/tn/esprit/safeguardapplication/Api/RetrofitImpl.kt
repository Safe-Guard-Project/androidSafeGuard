package tn.esprit.safeguardapplication.Api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitImpl {
    private const val BASE_URL = "http://192.168.1.16:9090/"

    private  val retrofit by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ProgrammeApi by lazy {
        retrofit.create(ProgrammeApi::class.java)
    }
    val commentApi: CommentApi by lazy {
        retrofit.create(CommentApi::class.java)
    }

    val favApi: FavApi by lazy {
        retrofit.create(FavApi::class.java)
    }
    val feedBApi :FeedBApi by lazy {
        retrofit.create(FeedBApi::class.java)
    }


}