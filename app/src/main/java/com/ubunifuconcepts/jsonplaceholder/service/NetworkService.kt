package com.ubunifuconcepts.jsonplaceholder.service

import com.ubunifuconcepts.jsonplaceholder.model.Post
import retrofit2.http.GET

interface NetworkService {
    @GET("posts")
    suspend fun fetchPosts(): List<Post>
}