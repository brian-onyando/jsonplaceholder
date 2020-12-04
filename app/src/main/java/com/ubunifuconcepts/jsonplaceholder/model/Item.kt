package com.ubunifuconcepts.jsonplaceholder.model

import com.google.gson.annotations.SerializedName

data class Item(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("body")
	val body: String,

	@field:SerializedName("userId")
	val userId: Int
)
