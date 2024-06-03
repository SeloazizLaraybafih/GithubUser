package com.example.selogu.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.selogu.data.model.User
import com.example.selogu.databinding.ItemsPeopleBinding

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: ItemsPeopleBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User){
        binding.root.setOnClickListener{
            onItemClickCallback?.onItemClicked(user)
        }

        Glide.with(itemView).load(user.avatarUrl).transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .into(binding.ivUser)

        binding.tvPpl.text = user.login
    }
}
    private val list = ArrayList<User>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(users: ArrayList<User>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {


        val view = ItemsPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder((view))
    }



    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    interface OnItemClickCallback {
        fun onItemClicked(data : User)
    }
}