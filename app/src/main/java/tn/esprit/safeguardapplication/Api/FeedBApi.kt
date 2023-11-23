package tn.esprit.safeguardapplication.Api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import tn.esprit.safeguardapplication.models.Commentaire

interface FeedBApi {
    @GET("commentairesProgramme/")
    suspend fun getComment(): Response<List<Commentaire>>
    @DELETE("commentairesProgramme/{id}")
    suspend fun deleteOnceComment(@Path("id")id:String): Response<ResponseBody>
    @PUT("commentairesProgramme/{id}")
    suspend fun UpdateC(@Path("id") id: String, @Body commentaire: Commentaire):Response<Commentaire>
}