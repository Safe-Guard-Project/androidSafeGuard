package tn.esprit.safeguardapplication.UI.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.safeguardapplication.UI.adapters.PInformationAdapter
import tn.esprit.safeguardapplication.databinding.FragmentProfilInfoBinding
import tn.esprit.safeguardapplication.models.Information

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfilInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfilInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentProfilInfoBinding


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
        // Inflate the layout for this fragment
        binding = FragmentProfilInfoBinding.inflate(inflater, container, false)
        binding.rvProfilInfo.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        return binding.root
    }
}




