package com.ubunifuconcepts.jsonplaceholder

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ubunifuconcepts.jsonplaceholder.model.Post
import com.ubunifuconcepts.jsonplaceholder.view.PostAdapter
import com.ubunifuconcepts.jsonplaceholder.viewmodel.PostsViewModel


class MainActivity : AppCompatActivity() {
    lateinit var postAdapter: PostAdapter
    lateinit var postsRecyclerView: RecyclerView
    lateinit var postsViewModel: PostsViewModel
    private val loadingVisual: AlertDialog by lazy {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.fetching_posts))
            .setCancelable(false)
            .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }

    private fun initialize() {
        setupRecyclerView()
        observePosts()
        fetchPostData()
        registerRefreshButtonListener()
    }

    private fun registerRefreshButtonListener() {
        val btnRefresh = findViewById<AppCompatButton>(R.id.btnRefresh)
        btnRefresh.setOnClickListener {
            fetchPostData()
        }
    }

    private fun fetchPostData() {
        showLoadingSpinner()
        postsViewModel.fetchPosts()
    }

    private fun setupRecyclerView() {
        postsRecyclerView = findViewById(R.id.rvItemList)
        postAdapter = PostAdapter()
        postsRecyclerView.layoutManager = LinearLayoutManager(this)
        postsRecyclerView.adapter = postAdapter
    }

    private fun observePosts() {
        postsViewModel = ViewModelProvider(this).get(PostsViewModel::class.java)
        postsViewModel.postsLiveData
            .observe(this, Observer<List<Post>> { posts ->
                if (posts.isNotEmpty()) {
                    updatePostsList(posts)
                } else {
                    showDataNotLoadedMessage()
                }
                hideLoadingSpinner()
            })
    }

    private fun showDataNotLoadedMessage() {
        Snackbar.make(
            postsRecyclerView,
            getString(R.string.data_not_loaded),
            Snackbar.LENGTH_LONG
        ).also { snackBar ->
            snackBar.show()
        }
    }

    private fun updatePostsList(posts: List<Post>) {
        postAdapter.setData(posts)
    }

    private fun showLoadingSpinner() {
        loadingVisual.show()
    }

    private fun hideLoadingSpinner() {
        loadingVisual.hide()
    }
}