package com.example.selogu.ui.dark

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.selogu.R
import com.example.selogu.data.local.SettingPreferences
import com.example.selogu.databinding.ActivityDarkBinding

class DarkActivity : AppCompatActivity() {

    private val dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private lateinit var binding: ActivityDarkBinding
    private val viewModel by viewModels<DarkViewModel>() {
            DarkViewModel.Factory(SettingPreferences.getInstance(dataStore))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDarkBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.getTheme().observe(this) {
            if(it){
                binding.switchDarkmode.text = "Dark Theme"
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                binding.switchDarkmode.text = "Light Theme"
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            binding.switchDarkmode.isChecked = it
        }

        binding.switchDarkmode.setOnCheckedChangeListener{_, isChecked ->
            viewModel.saveTheme(isChecked)
        }


         fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                android.R.id.home ->{
                    finish()
                }
            }
            return super.onOptionsItemSelected(item)
        }
    }
}