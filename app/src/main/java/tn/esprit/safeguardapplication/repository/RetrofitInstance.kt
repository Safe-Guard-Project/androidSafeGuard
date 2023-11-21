package tn.esprit.safeguardapplication.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.safeguardapplication.Api.CatastropheApi

object RetrofitInstance {
    val api: CatastropheApi by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl("http://192.168.43.130:9090/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatastropheApi::class.java)
    }
}