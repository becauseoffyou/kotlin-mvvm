package com.example.usergithubsearch.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usergithubsearch.databinding.ActivityMainBinding
import com.example.usergithubsearch.ui.detail.DetailUserActivity
import data.model.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MViewModel
    private lateinit var adapter : MainAdapterUser



    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MainAdapterUser()
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickCallback(object : MainAdapterUser.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.USERNAME, data.login)
                    startActivity(it)
                }
            }

        })
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MViewModel::class.java)

        binding.apply {
            rcvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rcvUser.setHasFixedSize(true)
            rcvUser.adapter = adapter
            edtSearch.setOnKeyListener { view, i, keyEvent ->
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        viewModel.ambilDataUser().observe(
            this,{
                if (it!=null){
                    adapter.setList(it)
                    showLoading(false)
                }
            }
        )
    }
    private fun searchUser(){
        binding.apply {
            val query = edtSearch.text.toString()
            if(query.isEmpty()) return
            showLoading(true)
            viewModel.setCariUser(query)
        }
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.pgb1.visibility = View.VISIBLE
        }else{
            binding.pgb1.visibility = View.GONE
        }
    }
}