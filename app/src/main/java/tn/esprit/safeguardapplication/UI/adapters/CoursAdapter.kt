package tn.esprit.safeguardapplication.UI.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


import tn.esprit.safeguardapplication.R
import tn.esprit.safeguardapplication.models.Cours


class CoursAdapter(private var contentList: List<Cours>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_CAUSE = 0
        const val VIEW_TYPE_CONSEQUENCE = 1
        const val VIEW_TYPE_SIGNE = 2
        const val VIEW_TYPE_AGIR = 3
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_CAUSE -> CauseViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recyclercause, parent, false)

            )
            VIEW_TYPE_CONSEQUENCE -> ConsequenceViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recyclerconsequence, parent, false)
            )
            VIEW_TYPE_SIGNE -> SigneViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recyclersigne, parent, false)
            )
            VIEW_TYPE_AGIR -> AgirViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycleragir, parent, false)
            )

            else -> throw IllegalArgumentException("Type de cours inconnu")
        }
    }


    override fun getItemCount(): Int {
        return contentList.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val content = contentList[position]
        when (holder.itemViewType) {
            VIEW_TYPE_CAUSE ->{
                val causeHolder = holder as CauseViewHolder
                causeHolder.bind(content)

                // Utilisez Picasso pour charger l'image dans l'ImageView

            }
            VIEW_TYPE_CONSEQUENCE -> (holder as ConsequenceViewHolder).bind(content)
            VIEW_TYPE_SIGNE -> (holder as SigneViewHolder).bind(content)
            VIEW_TYPE_AGIR -> (holder as AgirViewHolder).bind(content)

        }



    }
    fun updateData(newList: List<Cours>) {
        contentList = newList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val content = contentList.getOrNull(position)

        return when (content?.Type) {
            Cours.type.CAUSE -> VIEW_TYPE_CAUSE
            Cours.type.CONSEQUENCE -> VIEW_TYPE_CONSEQUENCE
            Cours.type.SIGNE -> VIEW_TYPE_SIGNE
            Cours.type.Agir -> VIEW_TYPE_AGIR
            else -> {

                Log.e("CoursAdapter", "Type de cours inconnu: ${content?.Type}")


            }
        }
    }



    class CauseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imagecause)

        fun bind(content: Cours) {

            itemView.findViewById<TextView>(R.id.txtcause)?.text = content.description
            Glide.with(itemView.context)
                .load(content.image)
                .into(imageView)
        }
    }
    class ConsequenceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imagecons)
        fun bind(content: Cours) {
            itemView.findViewById<TextView>(R.id.txtcons)?.text = content.description
            Glide.with(itemView.context)
                .load( content.image)
                .into(imageView)
        }
    }

    class SigneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageSigne)
        fun bind(content: Cours) {
            itemView.findViewById<TextView>(R.id.txtSigne)?.text = content.description
            Glide.with(itemView.context)
                .load(content.image)
                .into(imageView)
        }
    }
    class AgirViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(content: Cours) {
            itemView.findViewById<TextView>(R.id.textView8)?.text = content.description
        }
    }

}


