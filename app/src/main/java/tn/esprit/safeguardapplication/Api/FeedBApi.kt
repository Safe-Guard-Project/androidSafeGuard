package tn.esprit.safeguardapplication.Api

import retrofit2.Response
import retrofit2.http.GET
import tn.esprit.safeguardapplication.models.Commentaire

interface FeedBApi {
    @GET("commentairesProgramme/")
    suspend fun getComment(): Response<List<Commentaire>>
}