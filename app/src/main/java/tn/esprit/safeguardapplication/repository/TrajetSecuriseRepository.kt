package tn.esprit.safeguardapplication.repository

import androidx.lifecycle.LiveData
import tn.esprit.safeguardapplication.models.TarjetSecurise

interface TrajetSecuriseRepository {
    fun getTrajetSecurise(): LiveData<List<TarjetSecurise>>
}