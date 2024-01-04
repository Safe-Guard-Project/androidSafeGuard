package tn.esprit.safeguardapplication.UI.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.safeguardapplication.databinding.RecyclerfeedBinding
import tn.esprit.safeguardapplication.models.Commentaire

class FeedAdapter: RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
    private var onDeleteClick: ((position: Int) -> Unit)? = null
    private var onEditClickListener: ((position: Int) -> Unit)? = null
    fun setOnDeleteClick(listener: (position: Int) -> Unit) {
        onDeleteClick = listener
    }

    fun deleteOnceRess(position: Int) {
        onDeleteClick?.invoke(position)
    }
    fun setOnEditClickListener(listener: (position: Int) -> Unit) {
        onEditClickListener = listener
    }
    fun editcomment(position: Int) {
        onEditClickListener?.invoke(position)
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Commentaire>() {
        override fun areContentsTheSame(oldItem: Commentaire, newItem: Commentaire): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areItemsTheSame(oldItem: Commentaire, newItem: Commentaire): Boolean {
            return oldItem._id == newItem._id
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var commentaire: MutableList<Commentaire>
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
            buttonSupp.setOnClickListener { deleteOnceRess(holder.absoluteAdapterPosition) }


        }
    }

    inner class ViewHolder(val binding: RecyclerfeedBinding) : RecyclerView.ViewHolder(binding.root)
}