package tn.esprit.safeguardapplication.repository

import androidx.lifecycle.LiveData
import tn.esprit.safeguardapplication.models.Catastrophe

interface CatastropheRepository {
    fun getCatastrophes(): LiveData<List<Catastrophe>>
}