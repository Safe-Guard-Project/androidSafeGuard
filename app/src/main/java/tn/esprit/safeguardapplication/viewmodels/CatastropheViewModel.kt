// CatastropheViewModel.kt
package tn.esprit.t1.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import tn.esprit.safeguardapplication.TAG
import tn.esprit.safeguardapplication.repository.RetrofitInstance

class CatastropheViewModel : ViewModel() {

    fun getCatastrophes() = liveData(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.api.getCatastophes()
            emit(response.body())
        } catch (e: Exception) {
            Log.e(TAG, "Exception: ${e.message}")
            emit(null)
        }
    }
}
