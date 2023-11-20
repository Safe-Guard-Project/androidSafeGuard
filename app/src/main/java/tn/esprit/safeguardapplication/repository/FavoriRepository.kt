package tn.esprit.safeguardapplication.repository

import retrofit2.Response
import tn.esprit.safeguardapplication.models.Commentaire
import tn.esprit.safeguardapplication.models.Favori

interface FavoriRepository {
    fun addFav(favori: Favori): Response<Favori>
}