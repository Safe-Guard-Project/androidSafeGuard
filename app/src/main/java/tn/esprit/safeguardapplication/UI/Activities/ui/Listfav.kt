package tn.esprit.safeguardapplication.UI.Activities.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

import tn.esprit.safeguardapplication.UI.adapters.FavorieAdapter

import tn.esprit.safeguardapplication.databinding.ActivityListfavBinding
import tn.esprit.safeguardapplication.viewmodels.FavoriViewModel


class Listfav : AppCompatActivity() {
    private lateinit var favorieAdapter: FavorieAdapter
    private lateinit var viewModel: FavoriViewModel
    private lateinit var binding: ActivityListfavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListfavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(FavoriViewModel::class.java)

        binding.rvFavorie.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        favorieAdapter = FavorieAdapter()
        binding.rvFavorie.adapter = favorieAdapter

            observeViewModel()
    }
    private fun observeViewModel() {
        lifecycleScope.launch {
            binding.progressBar.isVisible = true

            viewModel.getFavorie().observe(this@Listfav, { FeecBack ->
                binding.progressBar.isVisible = false

                if (FeecBack != null) {
                    // Convertir la liste observée en MutableList
                    val mutableList = FeecBack.toMutableList()
                    favorieAdapter.fav = mutableList
                } else {
                    Log.e(FeedBack.TAG, "Error retrieving point collecte")
                }
            })
        }
    }

}