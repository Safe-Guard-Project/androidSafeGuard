package tn.esprit.safeguardapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tn.esprit.safeguardapplication.models.Catastrophe
import tn.esprit.safeguardapplication.repository.CatastropheRepository

class CatastropheViewModel(private val repository: CatastropheRepository) : ViewModel() {

    // LiveData to observe the list of catastrophes in your UI
    private val _catastrophes: LiveData<List<Catastrophe>> = repository.getCatastrophes()
    val catastrophes: LiveData<List<Catastrophe>> get() = _catastrophes
}