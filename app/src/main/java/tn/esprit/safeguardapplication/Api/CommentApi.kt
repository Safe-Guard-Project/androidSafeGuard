package tn.esprit.safeguardapplication.Api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import tn.esprit.safeguardapplication.models.Commentaire

interface CommentApi {

    @POST("commentairesProgramme/")
    suspend fun addComment (@Body commentaire: Commentaire): Response<Commentaire>
}