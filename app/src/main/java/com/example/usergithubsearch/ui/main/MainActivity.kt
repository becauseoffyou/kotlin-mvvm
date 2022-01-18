package com.example.usergithubsearch.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.usergithubsearch.R
import com.example.usergithubsearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}