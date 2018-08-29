package com.devandcoffee.iamalive

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class RecyclerViewAdapter(val users: ArrayList<User>, val context: Context):
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(users.get(position))
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItems(user: User) {
            itemView.user_email.text = user.email
            itemView.user_name.text = "${user.firstName} ${user.lastName}"
        }
    }
}