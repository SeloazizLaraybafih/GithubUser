package com.example.selogu.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.selogu.data.model.User
import com.example.selogu.databinding.ActivityMainBinding
import com.example.selogu.ui.detail.DetailActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeViews()
        initializeViewModel()
        observeSearchUser()
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
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
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
            startActivity(this)
        }
    }

    private fun showLoadingIndicator(show: Boolean) {
        binding.loading.visibility = if (show) View.VISIBLE else View.GONE
    }
}