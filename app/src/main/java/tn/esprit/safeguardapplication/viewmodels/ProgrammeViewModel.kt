package tn.esprit.safeguardapplication.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import tn.esprit.safeguardapplication.Api.RetrofitImpl

class ProgrammeViewModel : ViewModel(){
    fun getProgrammes() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitImpl.api.getProgrammes()
            emit(response.body())
        } catch (e: Exception) {
            Log.e(TAG, "Exception: ${e.message}")
            emit(null)
        }
    }
}