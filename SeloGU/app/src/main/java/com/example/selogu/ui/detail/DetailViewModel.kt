package com.example.selogu.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.selogu.api.RetrofitObj
import com.example.selogu.data.model.DetailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _user = MutableLiveData<DetailUserResponse>()
    val user: LiveData<DetailUserResponse> = _user

    fun setUserDetail(username: String) {
        val apiService = RetrofitObj.apiInstance
        val call = apiService.getUserDetail(username)
        call.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(call: Call<DetailUserResponse>, response: Response<DetailUserResponse>) {
                if (response.isSuccessful) {
                    val userData = response.body()
                    _user.value = userData
                } else {
                    Log.e("DetailViewModel", "Failed to fetch user details. Code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                Log.e("DetailViewModel", "Failed to fetch user details: ${t.message}", t)
            }
        })
    }
    fun getUserDetail(): LiveData<DetailUserResponse> {
        return user
    }
}