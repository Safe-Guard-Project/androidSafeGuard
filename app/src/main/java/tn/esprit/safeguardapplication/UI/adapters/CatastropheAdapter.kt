package tn.esprit.safeguardapplication.UI.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.safeguardapplication.databinding.ItemCatastropheBinding
import tn.esprit.safeguardapplication.models.Catastrophe

class CatastropheAdapter : RecyclerView.Adapter<CatastropheAdapter.CatastropheViewHolder>(),
    Filterable {

    private var originalList: List<Catastrophe> = emptyList()

    inner class CatastropheViewHolder(val binding: ItemCatastropheBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Catastrophe>() {
        override fun areContentsTheSame(oldItem: Catastrophe, newItem: Catastrophe): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areItemsTheSame(oldItem: Catastrophe, newItem: Catastrophe): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var catastrophes: List<Catastrophe>
        get() = differ.currentList
        set(value) {
            originalList = value
            differ.submitList(value)
        }

    override fun getItemCount() = catastrophes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatastropheViewHolder {
        return CatastropheViewHolder(
            ItemCatastropheBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatastropheViewHolder, position: Int) {
        holder.binding.apply {
            val catastrophe = catastrophes[position]
            tvType.text = "Type: ${catastrophe.type}"
            tvPlace.text = "Description: ${catastrophe.description}"
            tvMag.text = "Magnitude: ${catastrophe.magnitude.toString()} Richter "
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<Catastrophe>()

                if (constraint.isNullOrBlank()) {
                    filteredList.addAll(originalList)
                } else {
                    val filterPattern = constraint.toString().toLowerCase().trim()

                    for (catastrophe in originalList) {
                        if (catastrophe.description.lowercase().contains(filterPattern)) {
                            filteredList.add(catastrophe)
                        }
                    }
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                differ.submitList(results?.values as List<Catastrophe>)
            }
        }
    }
}
