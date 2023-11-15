package tn.esprit.safeguardapplication.UI.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.safeguardapplication.databinding.RecyclerchoixBinding
import tn.esprit.safeguardapplication.models.Choix


class ChoixAdapter (val choixList: MutableList<Choix>) : RecyclerView.Adapter<ChoixAdapter.ChoixHolder> (){
    inner class ChoixHolder (val binding: RecyclerchoixBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoixHolder {
        val binding =  RecyclerchoixBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChoixHolder(binding)
    }

    override fun getItemCount()= choixList.size

    override fun onBindViewHolder(holder: ChoixHolder, position: Int) {
        with(holder){
            with (choixList[position]){
                binding.btnchoix.text= text
            }

        }
    }

}