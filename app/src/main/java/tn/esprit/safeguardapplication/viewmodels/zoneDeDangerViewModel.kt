package tn.esprit.safeguardapplication.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import tn.esprit.safeguardapplication.repository.RetrofitInstance

class ZoneDeDangerViewModel : ViewModel() {
    fun getZoneDeDanger() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.ApiZoneDeDanger.getZoneDeDanger()
            if (response != null && response.isSuccessful) {
                emit(response.body())
            } else {
                Log.e(TAG, "Error: ${response?.errorBody()?.string()}")
                emit(null)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception: ${e.message}")
            emit(null)
        }
    }
}
