package com.ubunifuconcepts.jsonplaceholder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }

    private fun initialize() {
        setupRecyclerView()
        observePosts()
        fetchPostData()
    }

    private fun fetchPostData() {
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
}