package tn.esprit.safeguardapplication.Api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import tn.esprit.safeguardapplication.models.Favori
import tn.esprit.safeguardapplication.models.Programme

interface FavApi {
    @POST("favorie/")
suspend fun addFav(@Body favori: Favori): Response<Favori>
    @GET("favorie/cours")
    suspend fun getFavorie(): Response<List<Favori>>
}