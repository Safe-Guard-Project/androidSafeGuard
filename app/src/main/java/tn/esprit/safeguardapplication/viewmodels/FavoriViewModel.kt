package tn.esprit.safeguardapplication.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import okhttp3.ResponseBody
import retrofit2.Response
import tn.esprit.safeguardapplication.Api.RetrofitImpl
import tn.esprit.safeguardapplication.models.Favori

class FavoriViewModel : ViewModel(){
    suspend fun addFav(favori: Favori): Response<Favori> {

        return RetrofitImpl.favApi.addFav(favori)
    }
    suspend fun deleteOnceComment(id: String): Response<ResponseBody>? {
        try {
            return RetrofitImpl.feedBApi.deleteOnceComment(id)
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception: ${e.message}")
            return null
        }
    }
    suspend fun getFavorie() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitImpl.favApi.getFavorie()
            emit(response.body())
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception: ${e.message}")
            emit(null)
        }
    }
}