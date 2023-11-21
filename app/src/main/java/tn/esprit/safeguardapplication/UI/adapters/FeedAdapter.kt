package tn.esprit.safeguardapplication.UI.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.safeguardapplication.databinding.RecyclerfeedBinding
import tn.esprit.safeguardapplication.models.Commentaire

class FeedAdapter: RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Commentaire>() {
        override fun areContentsTheSame(oldItem: Commentaire, newItem: Commentaire): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areItemsTheSame(oldItem: Commentaire, newItem: Commentaire): Boolean {
            return oldItem._id == newItem._id
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var commentaire: List<Commentaire>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }

    override fun getItemCount() = commentaire.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerfeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val commentaire = commentaire[position]

            textfeedbk.text = commentaire.textComment

        }
    }

    inner class ViewHolder(val binding: RecyclerfeedBinding) : RecyclerView.ViewHolder(binding.root)
}