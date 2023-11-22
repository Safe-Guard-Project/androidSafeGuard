package tn.esprit.safeguardapplication.Api



import retrofit2.Response
import retrofit2.http.GET
import tn.esprit.safeguardapplication.models.ZoneDeDanger

interface ZoneDeDangerApi {

    @GET("zoneDeDanger/")
    suspend fun getZoneDeDanger(): Response<List<ZoneDeDanger>>?

}