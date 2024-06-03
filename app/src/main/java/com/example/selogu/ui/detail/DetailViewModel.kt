package com.example.selogu.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.selogu.api.RetrofitObj
import com.example.selogu.data.local.FavUser
import com.example.selogu.data.local.FavUserDao
import com.example.selogu.data.local.UserDB
import com.example.selogu.data.model.DetailUserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val _user = MutableLiveData<DetailUserResponse?>()
    val user: MutableLiveData<DetailUserResponse?> = _user

    private var userDao: FavUserDao?
    private var userDB: UserDB?
    init {
        userDB = UserDB.getDB(application)
        userDao = userDB?.favUserDao()
    }

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
    fun getUserDetail(): MutableLiveData<DetailUserResponse?> {
        return user
    }

    fun addToFav(username:String, id:Int,avatarUrl:String) {
        CoroutineScope(Dispatchers.IO).launch {
            var user = FavUser(
                username,
                id,
                avatarUrl
            )
            userDao?.addToFav(user)
        }
    }

    suspend fun checkUser(id: Int): Int {
        return userDao?.checkUser(id) ?:0
    }
    fun removeFromFav(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFromFav(id)
        }
    }
}