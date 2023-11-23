package tn.esprit.safeguardapplication.repository

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import tn.esprit.safeguardapplication.models.Programme

interface ProgrammeRepository {

    fun getProgrammes(): LiveData<List<Programme>>

    fun getProgrammesWithCours(): LiveData<List<Programme>>

}