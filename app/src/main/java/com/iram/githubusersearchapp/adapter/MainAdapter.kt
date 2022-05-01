package com.iram.githubusersearchapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iram.githubusersearchapp.R
import com.iram.githubusersearchapp.model.GithubUsers
import kotlinx.android.synthetic.main.item_layout.view.*
import javax.inject.Inject

class MainAdapter  @Inject constructor(
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    private var users: ArrayList<GithubUsers> = ArrayList()

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: GithubUsers) {
            itemView.tvUserName.text= user.login
            itemView.tvUserEmail.text = user.htmlUrl

            Glide.with(itemView.imgAvatar.context)
                .load(user.avatarUrl).error(R.drawable.ic_launcher_background)
                .into(itemView.imgAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(users: List<GithubUsers>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }
}