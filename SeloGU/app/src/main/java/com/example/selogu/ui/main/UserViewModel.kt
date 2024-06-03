package com.example.selogu.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.selogu.api.RetrofitObj
import com.example.selogu.data.model.User
import com.example.selogu.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserViewModel: ViewModel() {
    val listUser = MutableLiveData<ArrayList<User>>()

    fun setUsers(query: String) {
        RetrofitObj.apiInstance.getSearchUsers(query).enqueue(object: Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful) {
                listUser.postValue(response.body()?.items)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }

        })
    }

    fun getSearchUser(): LiveData<ArrayList<User>>{
        return listUser
    }
}