package tn.esprit.safeguardapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import tn.esprit.safeguardapplication.UI.adapters.CatastropheAdapter
import tn.esprit.safeguardapplication.databinding.ActivityCatastropheBinding
import tn.esprit.t1.viewmodel.CatastropheViewModel

const val TAG = "Catastrophe Activity"

class CatastropheActivity : ComponentActivity() {

    private lateinit var catastropheAdapter: CatastropheAdapter
    private lateinit var binding: ActivityCatastropheBinding
    private lateinit var viewModel: CatastropheViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCatastropheBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(CatastropheViewModel::class.java)

        setupRecyclerView()
        setupSearchView()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            binding.progressBar.isVisible = true

            viewModel.getCatastrophes().observe(this@CatastropheActivity, { catastrophes ->
                binding.progressBar.isVisible = false

                if (catastrophes != null) {
                    catastropheAdapter.catastrophes = catastrophes
                } else {
                    Log.e(TAG, "Error retrieving catastrophes")
                }
            })
        }
    }

    private fun setupRecyclerView() = binding.rvCatastrophe.apply {
        catastropheAdapter = CatastropheAdapter()
        adapter = catastropheAdapter
        layoutManager = LinearLayoutManager(this@CatastropheActivity)
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Clear focus when submit is pressed
                binding.searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                catastropheAdapter.filter.filter(newText)
                return false
            }
        })
    }
}
