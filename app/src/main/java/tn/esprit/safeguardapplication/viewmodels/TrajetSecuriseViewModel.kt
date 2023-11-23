package tn.esprit.safeguardapplication.viewmodels


import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import tn.esprit.safeguardapplication.repository.RetrofitInstance



class TrajetSecuriseViewModel : ViewModel() {

    fun   getTrajetSecurise() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.apiTarjetSecurise.getTrajetSecurise()
            if (response != null) {
                emit(response.body())
            }
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception: ${e.message}")
            emit(null)
        }
    }

    fun getTrajetSecuriseByIdUser(userId: String) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.apiTarjetSecurise.getTrajetSecuriseByIdUser(userId)
            emit(response.body())
        } catch (e: Exception) {
            Log.e(TAG, "Exception: ${e.message}")
            emit(null)
        }
    }

    fun changeEtatToFalseWithIdUser(userId: String) = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.apiTarjetSecurise.changeEtatToFalseWithIdUser(userId)
            emit(response.isSuccessful)
        } catch (e: Exception) {
            Log.e(TAG, "Exception: ${e.message}")
            emit(false)
        }
    }
}