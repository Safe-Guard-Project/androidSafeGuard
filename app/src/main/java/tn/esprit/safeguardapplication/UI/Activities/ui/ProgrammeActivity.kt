package tn.esprit.safeguardapplication.UI.Activities.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import tn.esprit.safeguardapplication.Api.ProgrammeApi
import tn.esprit.safeguardapplication.Api.RetrofitImpl

import tn.esprit.safeguardapplication.R
import tn.esprit.safeguardapplication.UI.adapters.ProgrammeAdapter
import tn.esprit.safeguardapplication.databinding.ActivityProgrammeBinding
import tn.esprit.safeguardapplication.models.Programme
import tn.esprit.safeguardapplication.viewmodels.ProgrammeViewModel

const val TAG = "Programme  Activity"
class ProgrammeActivity : AppCompatActivity() {
    private lateinit var programmeAdapter: ProgrammeAdapter
    private lateinit var binding: ActivityProgrammeBinding
    private lateinit var viewModel: ProgrammeViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgrammeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ProgrammeViewModel::class.java)

        setupRecyclerView()
        observeViewModel()
    }
    private fun observeViewModel() {
        lifecycleScope.launch {
            binding.progressBar.isVisible = true

            viewModel.getProgrammesWithCours().observe(this@ProgrammeActivity, { programmes ->
                binding.progressBar.isVisible = false

                if (programmes != null) {
                    programmeAdapter.programmes = programmes
                } else {
                    Log.e(TAG, "Error retrieving programmes")
                }
            })
        }
    }

    private fun setupRecyclerView() = binding.rvId.apply {
        programmeAdapter = ProgrammeAdapter()
        adapter = programmeAdapter
        layoutManager = LinearLayoutManager(this@ProgrammeActivity)
    }

} 


