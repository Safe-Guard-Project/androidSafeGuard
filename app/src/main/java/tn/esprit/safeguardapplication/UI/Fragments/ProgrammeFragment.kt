package tn.esprit.safeguardapplication.UI.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import tn.esprit.safeguardapplication.R
import tn.esprit.safeguardapplication.UI.adapters.ProgrammeAdapter
import tn.esprit.safeguardapplication.databinding.FragmentProgrammeBinding
import tn.esprit.safeguardapplication.viewmodels.ProgrammeViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


const val TAG = "Programme  Activity"
class ProgrammeFragment : Fragment() {

    private lateinit var programmeAdapter: ProgrammeAdapter
    private lateinit var binding:FragmentProgrammeBinding
    private lateinit var viewModel: ProgrammeViewModel


    /* override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         arguments?.let {
             param1 = it.getString(ARG_PARAM1)
             param2 = it.getString(ARG_PARAM2)
         }
     }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgrammeBinding.inflate(inflater,container,false)

        viewModel = ViewModelProvider(this).get(ProgrammeViewModel::class.java)

        setupRecyclerView()
        observeViewModel()
        return binding.root
    }
    private fun observeViewModel() {
        lifecycleScope.launch {
            binding.progressBar.isVisible = true

            val liveData = viewModel.getProgrammesWithCours()

            liveData.observe(viewLifecycleOwner) { programmes ->
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
        layoutManager = LinearLayoutManager(requireContext())
    }



}