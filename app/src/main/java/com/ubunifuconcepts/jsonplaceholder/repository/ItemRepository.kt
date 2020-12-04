package com.ubunifuconcepts.jsonplaceholder.repository

import android.util.Log
import com.ubunifuconcepts.common.network.RestClient
import com.ubunifuconcepts.jsonplaceholder.model.Post
import com.ubunifuconcepts.jsonplaceholder.service.NetworkService

object ItemRepository {
    suspend fun loadPosts(): List<Post> {
        return try {
            RestClient.service(NetworkService::class.java).fetchPosts()
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, "Error fetching posts: ${e.message}")
            listOf()
        }
    }
}