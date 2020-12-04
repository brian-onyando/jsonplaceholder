package com.ubunifuconcepts.jsonplaceholder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
                updatePostsList(posts)
            })
    }

    private fun updatePostsList(posts: List<Post>) {
        postAdapter.setData(posts)
    }
}