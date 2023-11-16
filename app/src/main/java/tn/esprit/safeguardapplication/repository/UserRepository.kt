package tn.esprit.safeguardapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tn.esprit.safeguardapplication.models.User

interface UserRepository {
    // This function should return LiveData<List<User>> after making the API call
    suspend fun signIn(email: String, password: String): MutableLiveData<User?>
    suspend fun signUp(UserName: String, email: String, password: String, phoneNumber: Int): LiveData<User?>
    suspend fun displayUserProfile(UserName: String, email: String, password: String, phoneNumber: Int): LiveData<User?>

    suspend fun displayUserProfile(userId: String): LiveData<User?>
}
