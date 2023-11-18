package tn.esprit.safeguardapplication.repository

import androidx.lifecycle.LiveData
import tn.esprit.safeguardapplication.models.Programme

interface ProgrammeRepository {
    fun getProgrammes(): LiveData<List<Programme>>

}