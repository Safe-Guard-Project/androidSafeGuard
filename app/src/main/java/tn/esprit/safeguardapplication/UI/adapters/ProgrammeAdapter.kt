package tn.esprit.safeguardapplication.UI.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.safeguardapplication.UI.Activities.CourrActivity
import tn.esprit.safeguardapplication.databinding.RecyclerprogBinding
import tn.esprit.safeguardapplication.models.Programme

class ProgrammeAdapter (val progList: MutableList<Programme>) : RecyclerView.Adapter<ProgrammeAdapter.ProgrammeHolder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgrammeHolder {
        val binding =  RecyclerprogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProgrammeHolder(binding)
    }

    override fun getItemCount()= progList.size


    override fun onBindViewHolder(holder: ProgrammeHolder, position: Int) {
        with(holder){
            with (progList[position]){
                binding.imageRV.setImageResource(image)
                binding.titreProg.text= Titre
                binding.descprog.text= descriptionProgramme

            }

        }

        holder.binding.card.setOnClickListener {
            val intent = Intent(it.context, CourrActivity::class.java)
            (it.context as Activity).startActivity(intent);
            //(it.context as Activity).finish();
        }



    }

    inner class ProgrammeHolder (val binding:RecyclerprogBinding) : RecyclerView.ViewHolder(binding.root)
}
