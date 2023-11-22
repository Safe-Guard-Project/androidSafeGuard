package tn.esprit.safeguardapplication.Api

import retrofit2.Response
import retrofit2.http.GET
import tn.esprit.safeguardapplication.models.TarjetSecurise

interface MapApiTarjetSecurise {
    @GET("trajetSecurise/")
    suspend fun getTrajetSecurise(): Response<List<TarjetSecurise>>?

}