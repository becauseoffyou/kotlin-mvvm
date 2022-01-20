package com.example.usergithubsearch.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.usergithubsearch.databinding.ItemRepoBinding
import data.model.UserRepo

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.UserViewHolder>() {
    private val list = ArrayList<UserRepo>()

    fun setList(users: ArrayList<UserRepo>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: UserRepo){
            binding.apply {
                val name = user.name.substring(0,1).toUpperCase() + user.name.substring(1).toLowerCase()
                androidCle.text = name
                thisIsAS.text = user.description
                starcount.text = user.stargazers_count.toString()

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder((view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}