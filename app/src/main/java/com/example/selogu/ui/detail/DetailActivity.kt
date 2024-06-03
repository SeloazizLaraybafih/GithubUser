package com.example.selogu.ui.detail


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.selogu.data.model.DetailUserResponse
import com.example.selogu.databinding.ActivityDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailActivity : AppCompatActivity() {



    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_URL = "extra_url"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID,0)
        val avatarUrl = intent.getStringExtra(EXTRA_URL)
        val bundle = Bundle().apply {
            putString(EXTRA_USERNAME, username)
        }

        initializeViewModel(username.toString())
        observeUserDetail()

        var isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count: Int? = viewModel.checkUser(id)
                withContext(Dispatchers.Main) {
                   if(count!=null){
                       if (count > 0){
                           binding.toggleFav.isChecked = true
                           isChecked = true
                       }
                       else{
                           binding.toggleFav.isChecked = false
                           isChecked = false
                       }
                   }
                }
        }

        // null error possibilities here
        binding.toggleFav.setOnClickListener{
            isChecked = !isChecked
            if (isChecked){
                if (username != null) {
                    if (avatarUrl != null) {
                        viewModel.addToFav(username,id,avatarUrl)
                    }
                }
            } else{
                viewModel.removeFromFav(id)
            }
            binding.toggleFav.isChecked = isChecked
        }


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


