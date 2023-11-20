package tn.esprit.safeguardapplication.viewmodels

import androidx.lifecycle.ViewModel
import retrofit2.Response
import tn.esprit.safeguardapplication.Api.RetrofitImpl
import tn.esprit.safeguardapplication.models.Commentaire
import tn.esprit.safeguardapplication.models.Favori

class FavoriViewModel : ViewModel(){
    suspend fun addFav(favori: Favori): Response<Favori> {

        return RetrofitImpl.favApi.addFav(favori)
    }

}