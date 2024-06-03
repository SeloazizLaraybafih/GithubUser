package com.example.selogu.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.selogu.R
import com.example.selogu.data.model.User
import com.example.selogu.databinding.FragmentFollowersBinding
import com.example.selogu.ui.main.UserAdapter

class FollowingFragment : Fragment(R.layout.fragment_followers) {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowingViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowersBinding.bind(view)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        val arg = arguments
        username = arg?.getString(DetailActivity.EXTRA_USERNAME).toString()
        adapter = UserAdapter().apply {
            setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: User) {
                    navigateToDetailActivity(data)
                }
            })
        }

        binding.apply {
            rvPpl.setHasFixedSize(true)
            rvPpl.layoutManager = LinearLayoutManager(activity)
            rvPpl.adapter = adapter
        }

        showLoadSign(true)
    }

    private fun observeViewModel() {
        viewModel = ViewModelProvider(this)[FollowingViewModel::class.java]
        viewModel.setListFollowing(username)
        viewModel.getListFollowingLiveData().observe(viewLifecycleOwner) { followingList: List<User>? ->
            followingList?.let {
                val arrayList = ArrayList(it) // Convert List<User> to ArrayList<User>
                adapter.setList(arrayList)
                showLoadSign(false)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoadSign(state: Boolean) {
        binding.loading.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun navigateToDetailActivity(user: User) {
        Intent(requireContext(),DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_USERNAME, user.login)
            putExtra(DetailActivity.EXTRA_ID,user.id)
            putExtra(DetailActivity.EXTRA_URL, user.avatarUrl)
            startActivity(this)
        }
    }
}
