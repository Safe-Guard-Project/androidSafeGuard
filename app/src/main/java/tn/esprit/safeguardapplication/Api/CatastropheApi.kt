package tn.esprit.safeguardapplication.Api

import retrofit2.Response
import retrofit2.http.GET
import tn.esprit.safeguardapplication.models.Catastrophe

interface CatastropheApi {

    @GET("catastrophe/")
    suspend fun getCatastophes(): Response<List<Catastrophe>>
}