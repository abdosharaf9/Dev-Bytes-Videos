package com.abdosharaf.devbytesvideos.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.abdosharaf.devbytesvideos.database.VideosDatabase
import com.abdosharaf.devbytesvideos.repositories.VideosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(database: VideosDatabase) : ViewModel() {

    private val repo = VideosRepository(database)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.refreshVideos()
        }
    }

    val playlist = repo.videosList
}

class ViewModelFactory(private val database: VideosDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(database) as T
        }
        throw IllegalArgumentException("Unable to construct viewModel")
    }
}