package com.iram.githubusersearchapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.iram.githubusersearchapp.R
import com.iram.githubusersearchapp.adapter.MainAdapter
import com.iram.githubusersearchapp.model.GithubUsers
import com.iram.githubusersearchapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var adapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupAPICall()
        observeSearchList()
        searchUser()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter()
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, 0))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupAPICall() {
        mainViewModel.userListLiveData.observe(this, Observer {
            if (it.isNotEmpty()) {
                recyclerView.visibility = View.VISIBLE
                tvError.visibility = View.GONE
                progressBar.visibility = View.GONE
                renderList(it)
            } else {
                hideRView()
            }
        })
        mainViewModel.loading.observe(this, {
            if (it) {
                recyclerView.visibility = View.GONE
                tvError.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            } else {
                showRView()
            }
        })
        mainViewModel.usersLoadError.observe(this, {
            if (it != null) {
                if (it.isNotEmpty()) {
                    hideRView()
                    tvError.text = it
                }
            }
        })
    }

    private fun showRView() {
        recyclerView.visibility = View.VISIBLE
        tvError.visibility = View.GONE
        progressBar.visibility = View.GONE
    }

    private fun hideRView() {
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.GONE
        tvError.visibility = View.VISIBLE
    }

    private fun renderList(users: List<GithubUsers>) {
        adapter.apply {
            addData(users)
            notifyDataSetChanged()
        }
    }

    private fun searchUser() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    mainViewModel.getUserListByName(query.trim())
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty()) {
                    mainViewModel.getUserListByName(newText.trim())
                }
                return true
            }
        })
    }

    private fun observeSearchList() {
        mainViewModel.searchUserListLiveData.observe(this, Observer {
            recyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            renderList(it)
        })
    }
}
