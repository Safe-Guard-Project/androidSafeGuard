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
import tn.esprit.safeguardapplication.R
import tn.esprit.safeguardapplication.UI.adapters.FeedAdapter
import tn.esprit.safeguardapplication.databinding.ActivityFeedBackBinding
import tn.esprit.safeguardapplication.databinding.ActivityProgrammeBinding
import tn.esprit.safeguardapplication.viewmodels.FeedViewModel


class FeedBack : AppCompatActivity() {
    private lateinit var feedAdapter: FeedAdapter
    private lateinit var viewModel: FeedViewModel
    private lateinit var binding : ActivityFeedBackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)

        binding.rvFeed.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        setupRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            binding.progressBar.isVisible = true

            viewModel.getComment().observe(this@FeedBack, { FeecBack ->
                binding.progressBar.isVisible = false

                if (FeecBack != null) {
                    feedAdapter.commentaire = FeecBack
                } else {
                    Log.e(TAG, "Error retrieving point collecte")
                }
            })
        }
    }

    private fun setupRecyclerView() = binding.rvFeed.apply {
       feedAdapter = FeedAdapter()
        adapter = feedAdapter
        layoutManager = LinearLayoutManager(this@FeedBack)
    }
    companion object{const val TAG = "Commentaire"}
    }

