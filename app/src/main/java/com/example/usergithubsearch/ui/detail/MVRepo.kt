package com.example.usergithubsearch.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import api.RClient
import data.model.UserRepo
import retrofit2.Call
import retrofit2.Response

class MVRepo : ViewModel() {
    val listUser = MutableLiveData<ArrayList<UserRepo>>()

    fun setCariRepoUser(username: String){
        RClient.instanceApi
            .ambilRepoUser(username)
            .enqueue(object : retrofit2.Callback<ArrayList<UserRepo>>{
                override fun onResponse(
                    call: Call<ArrayList<UserRepo>>,
                    response: Response<ArrayList<UserRepo>>
                ) {
                    if(response.isSuccessful){
                      listUser.postValue(response.body())
                   }
                }

                override fun onFailure(call: Call<ArrayList<UserRepo>>, t: Throwable) {
                    t.message?.let { Log.d("vvv", it) }
                }
            })
    }

    fun ambilRepoUser(): LiveData<ArrayList<UserRepo>> {
        return listUser
    }
}