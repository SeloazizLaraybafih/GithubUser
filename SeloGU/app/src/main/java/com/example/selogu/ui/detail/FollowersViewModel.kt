package com.example.selogu.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.selogu.api.RetrofitObj
import com.example.selogu.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {
    private val _listFollowers = MutableLiveData<ArrayList<User>>()
    private val listFollowers: LiveData<ArrayList<User>> = _listFollowers

    fun setListFollowers(username: String) {
        RetrofitObj.apiInstance.getFollowers(username).enqueue(object : Callback<ArrayList<User>> {
            override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                if (response.isSuccessful) {
                    _listFollowers.postValue(response.body())
                } else {
                    Log.e("FollowersViewModel", "Failed to fetch followers: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                Log.e("FollowersViewModel", "Failed to fetch followers: ${t.message}", t)
            }
        })
    }
    fun getListFollowers() : LiveData<ArrayList<User>> {
        return listFollowers
    }
}