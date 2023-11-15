package tn.esprit.safeguardapplication.UI.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.safeguardapplication.databinding.RecycleragirBinding
import tn.esprit.safeguardapplication.databinding.RecyclercauseBinding
import tn.esprit.safeguardapplication.models.Cours

class AgirAdapter(val agirList: MutableList<Cours>) : RecyclerView.Adapter<AgirAdapter.CoursHolder> () {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursHolder {
        val binding =  RecycleragirBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoursHolder(binding)
    }

    override fun getItemCount()=agirList.size
    override fun onBindViewHolder(holder: CoursHolder, position: Int) {
        with(holder){
            with (agirList[position]){

                binding.textView8.text= description


            }

        }
    }
    inner class CoursHolder (val binding: RecycleragirBinding) : RecyclerView.ViewHolder(binding.root)
}