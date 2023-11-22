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
import tn.esprit.safeguardapplication.models.Commentaire
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
                    // Convertir la liste observée en MutableList
                    val mutableList = FeecBack.toMutableList()
                    feedAdapter.commentaire = mutableList
                } else {
                    Log.e(TAG, "Error retrieving point collecte")
                }
            })
        }
    }
    private fun setupRecyclerView() = binding.rvFeed.apply {
       feedAdapter = FeedAdapter()


        // Ici, nous définissons ce qui se passe lorsque vous cliquez sur le bouton de suppression
        feedAdapter.setOnDeleteClick { position ->
            // Nous obtenons l'objet ReservationPc à la position spécifiée
            val commentaire = feedAdapter.commentaire[position]

            // Nous lançons une coroutine pour exécuter la suppression en arrière-plan
            lifecycleScope.launch {
                // Nous appelons la fonction deleteOnceRess sur le viewModel
                val response = viewModel.deleteOnceComment(commentaire._id)

                // Si la réponse est réussie, nous supprimons l'objet de la liste et nous notifions l'adaptateur
                if (response != null && response.isSuccessful) {
                    // Créer une nouvelle liste modifiable à partir de currentList
                    val newList = feedAdapter.commentaire.toMutableList()
                    // Supprimer l'élément de la nouvelle liste
                    newList.removeAt(position)
                    // Mettre à jour la liste dans l'adaptateur
                    feedAdapter.commentaire = newList
                } else {
                    // Sinon, nous enregistrons une erreur dans le journal
                    Log.e(TAG, "Error deleting reservationPc")
                }
            }
        }
        adapter = feedAdapter
        layoutManager = LinearLayoutManager(this@FeedBack)
    }
    companion object{const val TAG = "Commentaire"}
    }

