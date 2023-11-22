package tn.esprit.safeguardapplication.Api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import tn.esprit.safeguardapplication.models.Commentaire

interface CommentApi {

    @POST("commentairesProgramme/")
    suspend fun addComment (@Body commentaire: Commentaire): Response<Commentaire>
    @PUT("commentairesProgramme/{commentId}")
    suspend fun updateComment(@Path("commentId") commentId: String, @Body updateRequest: Commentaire): Response<Commentaire>

}