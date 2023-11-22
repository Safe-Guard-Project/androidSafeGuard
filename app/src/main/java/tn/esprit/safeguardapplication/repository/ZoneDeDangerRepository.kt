package tn.esprit.safeguardapplication.repository

import androidx.lifecycle.LiveData
import tn.esprit.safeguardapplication.models.ZoneDeDanger

interface ZoneDeDangerRepository {
    fun getZoneDeDanger(): LiveData<List<ZoneDeDanger>>
}