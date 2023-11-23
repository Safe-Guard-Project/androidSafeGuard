package tn.esprit.safeguardapplication.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import okhttp3.ResponseBody
import retrofit2.Response
import tn.esprit.safeguardapplication.Api.RetrofitImpl
import tn.esprit.safeguardapplication.models.Commentaire

class CommentaireViewModel : ViewModel(){
    suspend fun addComment(commentaire: Commentaire): Response<Commentaire> {

        return RetrofitImpl.commentApi.addComment(commentaire)
    }


}




