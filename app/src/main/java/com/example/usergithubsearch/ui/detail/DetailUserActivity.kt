package com.example.usergithubsearch.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.usergithubsearch.R
import com.example.usergithubsearch.databinding.ActivityDetailUserBinding
import com.example.usergithubsearch.ui.main.MViewModel
import com.example.usergithubsearch.ui.main.MainAdapterUser

class DetailUserActivity : AppCompatActivity() {

    companion object{
        const val  USERNAME = "user_name"
    }
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var  viewModel: DetailUserVM
    private lateinit var  repoModel: MVRepo
    private lateinit var adapter : RepoAdapter

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
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .circleCrop()
                        .into(imgprofil)
                }
            }
        })

        adapter = RepoAdapter()
        adapter.notifyDataSetChanged()

        repoModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MVRepo::class.java)

        binding.apply {
            rcvRepo.layoutManager = LinearLayoutManager(this@DetailUserActivity)
            rcvRepo.setHasFixedSize(true)
            rcvRepo.adapter = adapter


        }
        searchUser()
        repoModel.ambilRepoUser().observe(
            this,{

                Log.d("xxx",it.toString());
                if (it!=null){
                    adapter.setList(it)
                }
            }
        )
    }

    private fun searchUser(){
        binding.apply {
            repoModel.setCariRepoUser(intent.getStringExtra(USERNAME).toString())
        }
    }

}