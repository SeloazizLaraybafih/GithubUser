package com.example.selogu.ui.detail


import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.selogu.data.model.DetailUserResponse
import com.example.selogu.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {



    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle().apply {
            putString(EXTRA_USERNAME, username)
        }

        initializeViewModel(username.toString())
        observeUserDetail()

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }

    private fun initializeViewModel(username: String) {
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        viewModel.setUserDetail(username)
    }

    private fun observeUserDetail() {
        viewModel.getUserDetail().observe(this) { userDetail ->
            userDetail?.let {
                updateUI(it)
            }
        }
    }

    private fun updateUI(userDetail: DetailUserResponse) {
        showLoadingIndicator(true)

        binding.apply {
            Glide.with(this@DetailActivity)
                .load(userDetail.avatarUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(ivProfileUser)
            showLoadingIndicator(false)
            tvName.text = userDetail.name
            tvUsername.text = userDetail.login
            tvFollowers.text = "${userDetail.followers} Followers"
            tvFollowing.text = "${userDetail.following} Following"

        }
    }

    private fun showLoadingIndicator(show: Boolean) {
        binding.loading.visibility = if (show) View.VISIBLE else View.GONE
    }
}