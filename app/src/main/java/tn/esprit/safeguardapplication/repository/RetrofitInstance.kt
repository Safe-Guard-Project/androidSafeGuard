package tn.esprit.safeguardapplication.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import tn.esprit.safeguardapplication.Api.CatastropheApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.safeguardapplication.Api.MapApiTarjetSecurise
import tn.esprit.safeguardapplication.Api.UserApiService
import tn.esprit.safeguardapplication.Api.ZoneDeDangerApi
object RetrofitInstance {

    private const val BASE_URL = "http://192.168.1.105:9090/"

   
    
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: CatastropheApi by lazy {
        retrofit.create(CatastropheApi::class.java)
    }

    val apiUser: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }

    val ApiZoneDeDanger: ZoneDeDangerApi by lazy {
        retrofit.create(ZoneDeDangerApi::class.java)
    }
    val apiTarjetSecurise: MapApiTarjetSecurise by lazy {
        retrofit.create(MapApiTarjetSecurise::class.java)
    }

}

