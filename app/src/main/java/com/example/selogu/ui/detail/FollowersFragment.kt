package com.example.selogu.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.selogu.R
import com.example.selogu.databinding.FragmentFollowersBinding
import com.example.selogu.ui.main.UserAdapter


class FollowersFragment : Fragment(R.layout.fragment_followers) {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowersBinding.bind(view)
        initializeArguments()
        initializeViews()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeArguments() {
        arguments?.getString(DetailActivity.EXTRA_USERNAME)?.let { username = it }
    }

    private fun initializeViews() {
        adapter = UserAdapter()
        binding.rvPpl.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = this@FollowersFragment.adapter
        }
        showLoadingIndicator(true)
    }

    private fun observeViewModel() {
        viewModel = ViewModelProvider(this)[FollowersViewModel::class.java]
        viewModel.getListFollowers().observe(viewLifecycleOwner) { followers ->
            followers?.let {
                adapter.setList(it)
                showLoadingIndicator(false)
            }
        }
        viewModel.setListFollowers(username)
    }

    private fun showLoadingIndicator(show: Boolean) {
        binding.loading.visibility = if (show) View.VISIBLE else View.GONE
    }
}
