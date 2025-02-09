package com.example.selogu.ui.fav

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.selogu.data.local.FavUser
import com.example.selogu.data.local.FavUserDao
import com.example.selogu.data.local.UserDB

class FavViewModel(application: Application) : AndroidViewModel(application) {
    private var userDao: FavUserDao?
    private var userDB: UserDB?
    init {
        userDB = UserDB.getDB(application)
        userDao = userDB?.favUserDao()
    }

    fun getFavUser():LiveData<List<FavUser>>? {
        return userDao?.getFavUser()
    }

}