package tn.esprit.safeguardapplication.UI.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tn.esprit.safeguardapplication.Api.RetrofitImpl
import tn.esprit.safeguardapplication.R
import tn.esprit.safeguardapplication.databinding.ActivityProgrammeBinding
import tn.esprit.safeguardapplication.databinding.FragmentIntroBinding
import tn.esprit.safeguardapplication.models.Favori
import tn.esprit.safeguardapplication.viewmodels.CommentaireViewModel
import tn.esprit.safeguardapplication.viewmodels.FavoriViewModel

// TODO: Rename parameter arguments, choose names that match

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class introFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var isFavFilled = false
    private lateinit var binding: FragmentIntroBinding
    private lateinit var viewModel: FavoriViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroBinding.inflate(inflater, container, false)

        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FavoriViewModel::class.java)

        binding.favBtnV.setOnClickListener {

            if (isFavFilled) {

                binding.favBtnV.setImageResource(R.drawable.ic_favoritevide)
            } else {

                binding.favBtnV.setImageResource(R.drawable.ic_favorite)
                addToFavorites()
            }


            isFavFilled = !isFavFilled
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val introductionList = RetrofitImpl.api.getIntro()

                if (introductionList != null) {
                    withContext(Dispatchers.Main) {binding.textView5.text = introductionList[0].description
                    }
                } else {
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }
    private fun addToFavorites() {
        // Tu devrais remplacer ces valeurs par celles que tu veux envoyer au backend
        val favori = Favori(
            _id = "id de fav",
            idRessourceProgramme = "65590e31c34eba3a779aca70"

        )

        // Utiliser le viewModel pour ajouter aux favoris
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = viewModel.addFav(favori)


                if (response.isSuccessful) {

                    Log.d("LivraisonActivity", "Request successful")
                } else {
                    Log.e("LivraisonActivity", "Request failed: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("LivraisonActivity", "Exception: ${e.message}")
            }
        }
    }
}
