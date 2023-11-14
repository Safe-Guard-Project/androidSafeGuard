package tn.esprit.safeguardapplication.UI.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.safeguardapplication.databinding.RecyclercauseBinding

import tn.esprit.safeguardapplication.databinding.RecyclerconsequenceBinding
import tn.esprit.safeguardapplication.models.Cours

class ConsequenceAdapter (val consList: MutableList<Cours>) : RecyclerView.Adapter<ConsequenceAdapter.CoursHolder> () {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursHolder {
        val binding =  RecyclerconsequenceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoursHolder(binding)
    }

    override fun getItemCount()= consList.size

    override fun onBindViewHolder(holder: CoursHolder, position: Int) {
        with(holder){
            with (consList[position]){
                binding.imagecons.setImageResource(image)
                binding.txtcons.text= description


            }

        }
    }
    inner class CoursHolder (val binding: RecyclerconsequenceBinding)  : RecyclerView.ViewHolder(binding.root)
}