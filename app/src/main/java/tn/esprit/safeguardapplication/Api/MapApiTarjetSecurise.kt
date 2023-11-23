package tn.esprit.safeguardapplication.Api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import tn.esprit.safeguardapplication.models.TarjetSecurise

interface MapApiTarjetSecurise {
    @GET("trajetSecurise/")
    suspend fun getTrajetSecurise(): Response<List<TarjetSecurise>>?
    @GET("trajetSecurise/getTrajetSecuriseByIdUser/{id}")
    suspend fun getTrajetSecuriseByIdUser(@Path("id") userId: String): Response<TarjetSecurise>

    @PUT("trajetSecurise/changeEtatbyUserId/{id}")
    suspend fun changeEtatToFalseWithIdUser(@Path("id") userId: String): Response<Void>
}