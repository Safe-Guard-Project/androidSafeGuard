package tn.esprit.safeguardapplication.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import okhttp3.ResponseBody
import retrofit2.Response
import tn.esprit.safeguardapplication.Api.RetrofitImpl
import tn.esprit.safeguardapplication.models.Commentaire

class FeedViewModel : ViewModel(){
    fun getComment() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitImpl.feedBApi.getComment()
            emit(response.body())
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception: ${e.message}")
            emit(null)
        }
    }
    suspend fun deleteOnceComment(id: String): Response<ResponseBody>? {
        try {
            return RetrofitImpl.feedBApi.deleteOnceComment(id)
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception: ${e.message}")
            return null
        }
    }

    suspend fun UpdateC(id: String, commentaire: Commentaire): Response<Commentaire>{
        return RetrofitImpl.feedBApi.UpdateC(id,commentaire)
    }
/*
    suspend fun deleteOnceComment(commentId: String): Response<> {
        // Implémentation de la suppression du commentaire ici
    }*/
}