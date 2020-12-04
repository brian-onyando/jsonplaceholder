package com.ubunifuconcepts.jsonplaceholder.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ubunifuconcepts.jsonplaceholder.model.Post
import com.ubunifuconcepts.jsonplaceholder.repository.ItemRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostsViewModel : ViewModel() {
    val postsLiveData: MutableLiveData<List<Post>> = MutableLiveData()

    fun fetchPosts() {
        viewModelScope.launch(IO) {
            Log.d(javaClass.simpleName, "Fetching posts data")
            val posts = ItemRepository.loadPosts()
            withContext(Main) {
                postsLiveData.value = posts
            }
        }
    }

}