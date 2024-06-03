package com.example.selogu.ui.fav

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.selogu.data.local.FavUser
import com.example.selogu.data.model.User
import com.example.selogu.databinding.ActivityFavBinding
import com.example.selogu.ui.detail.DetailActivity
import com.example.selogu.ui.main.UserAdapter

class FavActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavViewModel



    private lateinit var binding: ActivityFavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavViewModel::class.java)

        adapter.setOnItemClickCallback(object:UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Intent(this@FavActivity,DetailActivity::class.java).also{
                    it.putExtra(DetailActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailActivity.EXTRA_ID,data.id)
                    it.putExtra(DetailActivity.EXTRA_URL, data.avatarUrl)
                    startActivity(it)
                }
            }

        })
        binding.apply {
            rvPpl.setHasFixedSize(true)
            rvPpl.layoutManager = LinearLayoutManager(this@FavActivity)
            rvPpl.adapter = adapter
        }
        viewModel.getFavUser()?.observe(this, {
            if(it!= null) {
                val list = mapList(it)
                adapter.setList(list)
            }
        })





    }

    private fun mapList(users: List<FavUser>): ArrayList<User> {
        val listUsers = ArrayList<User>()
        for (user in users) {
            val mapUser = User(
                user.login,
                user.id,
                user.avatar_url
            )
            listUsers.add(mapUser)
        }
        return listUsers
    }
}