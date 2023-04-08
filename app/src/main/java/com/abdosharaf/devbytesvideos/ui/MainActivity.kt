package com.abdosharaf.devbytesvideos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.abdosharaf.devbytesvideos.database.getDatabase
import com.abdosharaf.devbytesvideos.databinding.ActivityMainBinding
import com.abdosharaf.devbytesvideos.viewModels.MainViewModel
import com.abdosharaf.devbytesvideos.viewModels.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = VideosAdapter()
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(getDatabase(applicationContext)))[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.rvVideos.adapter = adapter
        adapter.onItemClicked = {
            val intent = Intent(Intent.ACTION_VIEW, it.launchUri)
            startActivity(intent)
        }
    }
}