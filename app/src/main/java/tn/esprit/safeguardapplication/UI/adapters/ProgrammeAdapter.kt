package tn.esprit.safeguardapplication.UI.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.safeguardapplication.UI.Activities.CourrActivity
import tn.esprit.safeguardapplication.databinding.RecyclerprogBinding
import tn.esprit.safeguardapplication.models.Programme

class ProgrammeAdapter : RecyclerView.Adapter<ProgrammeAdapter.ProgrammeViewHolder> (){

    private val diffCallback = object : DiffUtil.ItemCallback<Programme>(){
        override fun areContentsTheSame(oldItem: Programme, newItem: Programme): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areItemsTheSame(oldItem: Programme, newItem: Programme): Boolean {
            return oldItem==newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var programmes: List<Programme>
        get() = differ.currentList
        set(value) { differ.submitList(value)}
    override fun getItemCount()= programmes.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgrammeViewHolder {
        val binding =  RecyclerprogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProgrammeViewHolder(binding)
    }




    override fun onBindViewHolder(holder: ProgrammeViewHolder, position: Int) {

        holder.binding.apply {
            val programme = programmes[position]
            
            titreProg.text= programme.Titre
            descprog.text= programme.descriptionProgramme

        }

        holder.binding.card.setOnClickListener {
            val intent = Intent(it.context, CourrActivity::class.java)
            (it.context as Activity).startActivity(intent);
            //(it.context as Activity).finish();
        }



    }

    inner class ProgrammeViewHolder(val binding:RecyclerprogBinding) : RecyclerView.ViewHolder(binding.root)

}