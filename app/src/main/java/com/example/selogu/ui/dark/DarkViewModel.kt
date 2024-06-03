package com.example.selogu.ui.dark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.selogu.data.local.SettingPreferences
import kotlinx.coroutines.launch

class DarkViewModel(private val pref: SettingPreferences): ViewModel() {

    fun getTheme() = pref.getThemeSetting().asLiveData()

    fun saveTheme(isDark: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDark)
        }
    }



    class Factory(private val pref: SettingPreferences) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>) : T = DarkViewModel(pref) as T
    }
}