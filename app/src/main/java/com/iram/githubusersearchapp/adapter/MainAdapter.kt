package com.iram.githubusersearchapp.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.iram.githubusersearchapp.R
import com.iram.githubusersearchapp.model.GithubUsers
import kotlinx.android.synthetic.main.item_layout.view.*
import javax.inject.Inject


class MainAdapter  @Inject constructor(
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    private var users: ArrayList<GithubUsers> = ArrayList()

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(holder: DataViewHolder, user: GithubUsers) {
            itemView.tvUserName.text= user.login
            itemView.tvUserEmail.text = user.htmlUrl

            Glide.with(itemView.imgAvatar.context)
                .load(user.avatarUrl).transform(CircleCrop())
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.imgAvatar)

            holder.itemView.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(user.htmlUrl))
                startActivity(itemView.context, browserIntent, null)
            }
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
        holder.bind(holder, users[position])

    fun addData(users: List<GithubUsers>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }

}