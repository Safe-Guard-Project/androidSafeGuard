package tn.esprit.safeguardapplication.UI.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tn.esprit.safeguardapplication.databinding.RecyclerfavorieBinding

import tn.esprit.safeguardapplication.models.Favori


class FavorieAdapter : RecyclerView.Adapter<FavorieAdapter.ViewHolder>() {
    private val diffCallback = object : DiffUtil.ItemCallback<Favori>() {
        override fun areContentsTheSame(oldItem: Favori, newItem: Favori): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areItemsTheSame(oldItem: Favori, newItem: Favori): Boolean {
            return oldItem._id == newItem._id
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var fav: MutableList<Favori>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemCount() = fav.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavorieAdapter.ViewHolder {
        val binding =  RecyclerfavorieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavorieAdapter.ViewHolder, position: Int) {
        val currentFavori = fav[position]

        Glide.with(holder.itemView.context)
            .load(currentFavori.idCoursProgramme.image)
            .into(holder.binding.imageFav)

        holder.binding.txtfav.text = currentFavori.idCoursProgramme.description


    }

    inner class ViewHolder(val binding: RecyclerfavorieBinding) : RecyclerView.ViewHolder(binding.root)

}