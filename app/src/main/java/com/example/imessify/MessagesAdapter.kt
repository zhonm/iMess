package com.example.imessify

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MessagesAdapter(private val context: Context, private val userAdapterList: ArrayList<UserAdapter>) :
    RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    // Add a variable to store the long click listener
    private var onItemLongClickListener: (() -> Unit)? = null

    // Method to set the long click listener
    fun setOnItemLongClickListener(listener: () -> Unit) {
        onItemLongClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_message, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userAdapterList[position]
        Glide.with(context).load(user.image_id).into(holder.imageView)
        holder.username.text = user.name
        holder.lastMessage.text = user.last_Message
        holder.lastMsgTime.text = user.last_Msg_time

        // Add long click listener to the item view
        holder.itemView.setOnLongClickListener {
            onItemLongClickListener?.invoke()
            true
        }
    }

    override fun getItemCount(): Int = userAdapterList.size

    // Add this method to support filtered lists
    fun updateList(newList: ArrayList<UserAdapter>) {
        userAdapterList.clear()
        userAdapterList.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.profile_pic)
        val username: TextView = itemView.findViewById(R.id.person_name)
        val lastMessage: TextView = itemView.findViewById(R.id.last_message)
        val lastMsgTime: TextView = itemView.findViewById(R.id.msg_time)
    }
}