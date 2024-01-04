package tn.esprit.safeguardapplication.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch

import tn.esprit.safeguardapplication.R
import tn.esprit.safeguardapplication.UI.Fragments.TAG
import tn.esprit.safeguardapplication.UI.adapters.ProgrammeAdapter
import tn.esprit.safeguardapplication.databinding.ActivityCourrBinding
import tn.esprit.safeguardapplication.databinding.ActivityProgrammeBinding
import tn.esprit.safeguardapplication.databinding.FragmentProgrammeBinding
import tn.esprit.safeguardapplication.viewmodels.ProgrammeViewModel

class ProgrammeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProgrammeBinding
    private lateinit var programmeAdapter: ProgrammeAdapter

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

            val liveData = viewModel.getProgrammesWithCours()

            liveData.observe(this@ProgrammeActivity) { programmes ->
                binding.progressBar.isVisible = false

                if (programmes != null) {
                    programmeAdapter.programmes = programmes
                } else {
                    Log.e(TAG, "Error retrieving programmes")
                }
            }
        }
    }
    private fun setupRecyclerView() = binding.rvId.apply {
        programmeAdapter = ProgrammeAdapter()
        adapter = programmeAdapter
        layoutManager = LinearLayoutManager(this@ProgrammeActivity)
    }
}