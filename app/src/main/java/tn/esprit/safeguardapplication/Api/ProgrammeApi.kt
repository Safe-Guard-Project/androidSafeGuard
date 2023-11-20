package tn.esprit.safeguardapplication.Api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import tn.esprit.safeguardapplication.models.Cours
import tn.esprit.safeguardapplication.models.Programme

interface ProgrammeApi {

    @GET("programme/")
    suspend fun getProgrammes(): Response<List<Programme>>
    @GET("cours/")
    suspend fun getCours(): List<Cours>
    @GET("cours/CAUSE")
    suspend fun getCauses(): List<Cours>

    @GET("cours/CONSEQUENCE")
    suspend fun getConsequences(): List<Cours>

    @GET("cours/SIGNE")
    suspend fun getSignes(): List<Cours>
    @GET("cours/Agir")
    suspend fun getAgir(): List<Cours>
    @GET("cours/Introduction")
    suspend fun getIntro(): List<Cours>
    @GET("cours/idProg/{id}")
    suspend fun getCoursByProgramme(@Path("id") progId:String): List<Cours>



}