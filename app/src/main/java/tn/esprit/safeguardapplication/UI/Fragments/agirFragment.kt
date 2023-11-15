package tn.esprit.safeguardapplication.UI.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.safeguardapplication.R
import tn.esprit.safeguardapplication.UI.adapters.AgirAdapter
import tn.esprit.safeguardapplication.UI.adapters.ConsequenceAdapter
import tn.esprit.safeguardapplication.databinding.FragmentAgirBinding
import tn.esprit.safeguardapplication.databinding.FragmentCauseBinding
import tn.esprit.safeguardapplication.databinding.FragmentConsequenceBinding
import tn.esprit.safeguardapplication.models.Cours

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [agirFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class agirFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentAgirBinding

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
    ):View? {
        binding = FragmentAgirBinding.inflate(inflater, container, false)


        binding.rvAgir.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvAgir.adapter = AgirAdapter(getAgirList(requireContext()))
        return binding.root
    }

    private fun getAgirList(context: Context) : MutableList<Cours>{
        return  mutableListOf(
            Cours(R.drawable.tsunamii,"ok",0),
            Cours(R.drawable.tremblement ,"Tremblement",0)
        )

    }
}