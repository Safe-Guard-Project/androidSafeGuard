package tn.esprit.safeguardapplication.Api

import retrofit2.Call
import retrofit2.http.*
import tn.esprit.safeguardapplication.models.TarjetSecurise

interface MapApiTarjetSecurise {
    @GET("trajetSecurise") // Adjust the endpoint path as needed
    fun getTarjetSecurises(): Call<List<TarjetSecurise>>

    @POST("trajetSecurise") // Adjust the endpoint path as needed
    fun createTarjetSecurise(@Body tarjetSecurise: TarjetSecurise): Call<TarjetSecurise>

    @PUT("trajetSecurise/{id}") // Adjust the endpoint path as needed
    fun updateTrajetSecurise(@Path("id") id: String, @Body tarjetSecurise: TarjetSecurise): Call<TarjetSecurise>

    @DELETE("trajetSecurise/{id}") // Adjust the endpoint path as needed
    fun deleteTrajetSecurise(@Path("id") id: String): Call<Void>
}