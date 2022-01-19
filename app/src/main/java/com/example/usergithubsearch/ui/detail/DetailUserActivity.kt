package com.example.usergithubsearch.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.usergithubsearch.R
import com.example.usergithubsearch.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    companion object{
        const val  USERNAME = "user_name"
    }
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var  viewModel: DetailUserVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ambilnama = intent.getStringExtra(USERNAME)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(DetailUserVM::class.java)
        viewModel.setCariDetailUser(ambilnama.toString())

        viewModel.ambilDetailUser().observe(this,{
            if(it != null){
                binding.apply {
                    val usernames = it.name.substring(0,1).toUpperCase() + it.name.substring(1).toLowerCase()
                    name.text = usernames
                    twitter.text = "@"+it.twitter_username
                    bio.text = it.bio
                    Log.d("xxx",it.toString()+it.avatar_url)
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .circleCrop()
                        .into(imgprofil)
                }
            }
        })


    }
}