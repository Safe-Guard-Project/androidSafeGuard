package tn.esprit.safeguardapplication.UI.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.safeguardapplication.R
import tn.esprit.safeguardapplication.UI.adapters.InformationAdapter
import tn.esprit.safeguardapplication.databinding.FragmentInformationBinding
import tn.esprit.safeguardapplication.models.Information

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class InformationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentInformationBinding



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
        binding = FragmentInformationBinding.inflate(inflater, container, false)

        binding.rvInformation.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvInformation.adapter = InformationAdapter(getInfoList(requireContext()))
         val v= inflater.inflate(R.layout.recyclerinfo,container,false)
        val bt= v.findViewById<Button>(R.id.buttonShowMore)
        bt.setOnClickListener(){
            val detailinfo = DetailInfoFragment()
            val transaction: FragmentTransaction =  requireFragmentManager().beginTransaction()
            transaction.replace(R.id.rvInformation,detailinfo)
            transaction.commit()

        }
        return binding.root




    }

    private fun getInfoList(requireContext: Context): MutableList<Information> {
            return  mutableListOf(
                Information("tsunami!!!!!!!!!","tsunami","","","","ljmkmjm",0,30,1),
                Information("inondation!!!!!!!!!","tsunami","","","","ljmkmjm",0,30,1),

                )

        }
    }




