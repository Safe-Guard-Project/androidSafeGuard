package tn.esprit.safeguardapplication.UI.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tn.esprit.safeguardapplication.UI.Activities.CourrActivity
import tn.esprit.safeguardapplication.databinding.RecyclerprogBinding
import tn.esprit.safeguardapplication.models.Programme

class ProgrammeAdapter : RecyclerView.Adapter<ProgrammeAdapter.ViewHolder> (){

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =  RecyclerprogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }




    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.apply {
            val programme = programmes[position]
            Glide.with(root.context)
                .load("http://10.0.2.2:9090/"+programme.image).into(imageRV)
            titreProg.text= programme.Titre
            descprog.text= programme.descriptionProgramme
        }

        holder.binding.card.setOnClickListener {
            val intent = Intent(it.context, CourrActivity::class.java)
            (it.context as Activity).startActivity(intent);
            //(it.context as Activity).finish();
        }



    }

    inner class ViewHolder(val binding:RecyclerprogBinding) : RecyclerView.ViewHolder(binding.root)

}