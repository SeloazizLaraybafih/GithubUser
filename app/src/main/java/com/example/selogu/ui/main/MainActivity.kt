package com.example.selogu.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.selogu.R
import com.example.selogu.data.local.SettingPreferences
import com.example.selogu.data.model.User
import com.example.selogu.databinding.ActivityMainBinding
import com.example.selogu.ui.dark.DarkActivity
import com.example.selogu.ui.dark.DarkViewModel
import com.example.selogu.ui.detail.DetailActivity
import com.example.selogu.ui.fav.FavActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: UserViewModel
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeViews()
        initializeViewModel()
        observeSearchUser()
        viewModel.getTheme().observe(this) {
            if(it){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.fav_menu -> {
                Intent(this,FavActivity::class.java).also{
                    startActivity(it)
                }
            }
            R.id.darkmode -> {
                Intent(this, DarkActivity::class.java).also{
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initializeViews() {
        adapter = UserAdapter().apply {
            setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: User) {
                    navigateToDetailActivity(data)
                }
            })
        }
        binding.rvPpl.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = this@MainActivity.adapter
        }
        binding.btnSearch.setOnClickListener { searchUser() }
        binding.etQuery.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchUser()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun initializeViewModel() {
        val settingPreferences = SettingPreferences.getInstance(applicationContext.dataStore)
        val viewModelFactory = UserViewModel.Factory(settingPreferences)
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
    }

    private fun observeSearchUser() {
        viewModel.getSearchUser().observe(this) { userList ->
            adapter.setList(userList)
            showLoadingIndicator(false)
        }
    }

    private fun searchUser() {
        val query = binding.etQuery.text.toString()
        if (query.isNotEmpty()) {
            showLoadingIndicator(true)
            viewModel.setUsers(query)
        }
    }

    private fun navigateToDetailActivity(user: User) {
        Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_USERNAME, user.login)
            putExtra(DetailActivity.EXTRA_ID,user.id)
            putExtra(DetailActivity.EXTRA_URL, user.avatarUrl)
            startActivity(this)
        }
    }

    private fun showLoadingIndicator(show: Boolean) {
        binding.loading.visibility = if (show) View.VISIBLE else View.GONE
    }


}