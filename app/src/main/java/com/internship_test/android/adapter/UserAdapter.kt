package com.internship_test.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.internship_test.android.MainActivity
import com.internship_test.android.R
import com.internship_test.android.user.User

class UserAdapter(private val listener: Listener, private val user: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userName: TextView = itemView.findViewById(R.id.userName)
        var userAge: TextView = itemView.findViewById(R.id.userAge)
        var userIsStudent: TextView = itemView.findViewById(R.id.userIsStudent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.users, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.userName.text = user[position].name
        holder.userAge.text = user[position].age.toString()
        holder.userIsStudent.text = user[position].isStudent.toString()

        holder.itemView.setOnClickListener {
            listener.onClick(user[position])
        }
    }

    override fun getItemCount() = user.size

    interface Listener {
        fun onClick(user: User) {
        }
    }
}