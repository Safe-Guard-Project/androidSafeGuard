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
import tn.esprit.safeguardapplication.databinding.FragmentAgirBinding

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
    ):View? {
        val view = inflater.inflate(R.layout.fragment_agir, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.rvAgir)
        coursAdapter = CoursAdapter(emptyList())
        recyclerView.adapter = coursAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val agirList = RetrofitImpl.api.getAgir()

                if (agirList  != null) {

                    withContext(Dispatchers.Main) {
                        coursAdapter.updateData(agirList )
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