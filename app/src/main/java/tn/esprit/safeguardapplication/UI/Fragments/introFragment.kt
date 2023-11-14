package tn.esprit.safeguardapplication.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tn.esprit.safeguardapplication.R
import tn.esprit.safeguardapplication.databinding.ActivityProgrammeBinding
import tn.esprit.safeguardapplication.databinding.FragmentIntroBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [introFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class introFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var isFavFilled = false
    private lateinit var binding: FragmentIntroBinding

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

        /*return inflater.inflate(R.layout.fragment_intro, container, false)*/
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Gérez le clic sur le bouton de cœur ici
        binding.favBtnV.setOnClickListener {
            // Changez l'image du cœur en fonction de l'état actuel
            if (isFavFilled) {
                // Si le cœur est rempli, changez à l'état vide
                binding.favBtnV.setImageResource(R.drawable.ic_favoritevide)
            } else {
                // Si le cœur est vide, changez à l'état rempli
                binding.favBtnV.setImageResource(R.drawable.ic_favorite)
            }

            // Inversez l'état actuel du cœur
            isFavFilled = !isFavFilled
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            introFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}