package tn.esprit.safeguardapplication.Api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import tn.esprit.safeguardapplication.models.Favori

interface FavApi {
    @POST("favorie/")
suspend fun addFav(@Body favori: Favori): Response<Favori>
}