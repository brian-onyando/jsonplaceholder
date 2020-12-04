package com.ubunifuconcepts.jsonplaceholder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }

    private fun initialize() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        TODO("Not yet implemented")
    }
}