package com.example.usergithubsearch.ui.main


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import api.RClient
import data.model.User
import data.model.UserResponse
import retrofit2.Response

class MViewModel : ViewModel(){
    val listUser = MutableLiveData<ArrayList<User>>()

    fun setCariUser(query: String){
        RClient.instanceApi
            .ambilDataUser(query)
            .enqueue(object : retrofit2.Callback<UserResponse>{
                override fun onResponse(
                    call: retrofit2.Call<UserResponse>,
                    response: Response<UserResponse>
                ){
                    if(response.isSuccessful){
                        listUser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: retrofit2.Call<UserResponse>, throwable: Throwable){
                    throwable.message?.let { Log.d("Failure", it) }
                }
            })
    }

    fun ambilDataUser(): LiveData<ArrayList<User>>{
        return listUser
    }
}