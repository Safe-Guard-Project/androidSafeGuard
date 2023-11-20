package tn.esprit.safeguardapplication.viewmodels

import androidx.lifecycle.ViewModel
import retrofit2.Response
import tn.esprit.safeguardapplication.Api.RetrofitImpl
import tn.esprit.safeguardapplication.models.Commentaire

class CommentaireViewModel : ViewModel(){
    suspend fun addComment(commentaire: Commentaire): Response<Commentaire> {

        return RetrofitImpl.commentApi.addComment(commentaire)
    }
}