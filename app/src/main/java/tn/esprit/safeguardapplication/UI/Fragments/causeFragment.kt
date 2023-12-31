package tn.esprit.safeguardapplication.UI.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tn.esprit.safeguardapplication.Api.RetrofitImpl
import tn.esprit.safeguardapplication.R
import tn.esprit.safeguardapplication.UI.adapters.CoursAdapter
import tn.esprit.safeguardapplication.databinding.FragmentCauseBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [causeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class causeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentCauseBinding
    private lateinit var coursAdapter: CoursAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cause, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.rvCause)
        coursAdapter = CoursAdapter(emptyList())
        recyclerView.adapter = coursAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val causesList = RetrofitImpl.api.getCauses()

                if (causesList != null) {

                    withContext(Dispatchers.Main) {
                        coursAdapter.updateData(causesList)
                    }
                } else {

                }
            } catch (e: Exception) {

                e.printStackTrace()
            }
        }

        return view
    }
}
