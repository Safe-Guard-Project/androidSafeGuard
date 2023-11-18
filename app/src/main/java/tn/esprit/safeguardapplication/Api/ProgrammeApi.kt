package tn.esprit.safeguardapplication.Api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import tn.esprit.safeguardapplication.models.Programme

interface ProgrammeApi {




    @GET("programme/")
    suspend fun getProgrammes(): Response<List<Programme>>


}