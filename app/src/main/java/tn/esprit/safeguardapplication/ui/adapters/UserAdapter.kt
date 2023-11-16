package tn.esprit.safeguardapplication.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.safeguardapplication.R
import tn.esprit.safeguardapplication.models.User

class UserAdapter(private val context: Context) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUserName: TextView = itemView.findViewById(R.id.emailsi)
        val tvEmail: TextView = itemView.findViewById(R.id.passwordsi)
    }

    private val diffCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var users: List<User>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemCount() = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // Inflate the layout for the item view
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_sign_in, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.tvUserName.text = "Username: ${user.UserName}"
        holder.tvEmail.text = "Email: ${user.email}"
    }
}
