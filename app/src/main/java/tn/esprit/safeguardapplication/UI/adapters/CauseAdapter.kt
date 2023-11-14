package tn.esprit.safeguardapplication.UI.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.safeguardapplication.databinding.FragmentCauseBinding
import tn.esprit.safeguardapplication.databinding.RecyclercauseBinding
import tn.esprit.safeguardapplication.databinding.RecyclerprogBinding
import tn.esprit.safeguardapplication.models.Cours
import tn.esprit.safeguardapplication.models.Programme

class CauseAdapter (val causeList: MutableList<Cours>) : RecyclerView.Adapter<CauseAdapter.CoursHolder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursHolder {
        val binding =  RecyclercauseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoursHolder(binding)
    }

    override fun getItemCount()= causeList.size

    override fun onBindViewHolder(holder: CoursHolder, position: Int) {
        with(holder){
            with (causeList[position]){
                binding.imagecause.setImageResource(image)
                binding.txtcause.text= description


            }

        }
    }
    inner class CoursHolder (val binding: RecyclercauseBinding) : RecyclerView.ViewHolder(binding.root)

}