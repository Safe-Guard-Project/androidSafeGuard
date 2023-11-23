package tn.esprit.safeguardapplication.repository

import androidx.lifecycle.LiveData
import tn.esprit.safeguardapplication.models.Commentaire

interface FeedRepository {
    fun getComment(): LiveData<List<Commentaire>>
}