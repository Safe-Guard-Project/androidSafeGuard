package tn.esprit.safeguardapplication.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import tn.esprit.safeguardapplication.Api.RetrofitImpl

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
}