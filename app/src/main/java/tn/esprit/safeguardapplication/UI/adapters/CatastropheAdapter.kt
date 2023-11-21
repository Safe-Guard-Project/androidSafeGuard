package tn.esprit.safeguardapplication.UI.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.safeguardapplication.databinding.ItemCatastropheBinding
import tn.esprit.safeguardapplication.models.Catastrophe

class CatastropheAdapter: RecyclerView.Adapter<CatastropheAdapter.CatastropheViewHolder>() {
    inner class CatastropheViewHolder(val binding: ItemCatastropheBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Catastrophe>(){
        override fun areContentsTheSame(oldItem: Catastrophe, newItem: Catastrophe): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areItemsTheSame(oldItem: Catastrophe, newItem: Catastrophe): Boolean {
            return oldItem==newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var catastrophes: List<Catastrophe>
        get() = differ.currentList
        set(value) { differ.submitList(value)}

    override fun getItemCount()= catastrophes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatastropheViewHolder {
        return CatastropheViewHolder(ItemCatastropheBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: CatastropheViewHolder, position: Int) {
        holder.binding.apply {
            val catastrophe = catastrophes[position]
            tvType.text = "Type: ${catastrophe.type}"
            tvPlace.text = "Description: ${catastrophe.description}"
            tvMag.text = "Magnitude: ${catastrophe.magnitude.toString()} Richter "
        }
    }
}