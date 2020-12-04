package com.ubunifuconcepts.jsonplaceholder.view.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.ubunifuconcepts.jsonplaceholder.R
import com.ubunifuconcepts.jsonplaceholder.model.Post

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val userId: AppCompatTextView = itemView.findViewById(R.id.tvUserIdValue)
    private val postId: AppCompatTextView = itemView.findViewById(R.id.tvPostIdValue)
    private val postTitle: AppCompatTextView = itemView.findViewById(R.id.tvPostTitle)
    private val postBody: AppCompatTextView = itemView.findViewById(R.id.tvPostBody)

    fun setData(post: Post) {
        userId.text = post.userId.toString()
        postId.text = post.id.toString()
        postTitle.text = post.title
        postBody.text = post.body
    }
}