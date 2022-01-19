package com.example.usergithubsearch.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import api.RClient
import data.model.UserDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserVM: ViewModel() {
    val user = MutableLiveData<UserDetail>()

    fun setCariDetailUser(username: String){
        RClient.instanceApi
            .ambilDetailUser(username)
            .enqueue(object : Callback<UserDetail>{
                override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                    if(response.isSuccessful){
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun ambilDetailUser(): LiveData<UserDetail> {
        return user
    }
}