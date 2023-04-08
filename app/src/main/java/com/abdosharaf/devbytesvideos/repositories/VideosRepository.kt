package com.abdosharaf.devbytesvideos.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.abdosharaf.devbytesvideos.database.VideosDatabase
import com.abdosharaf.devbytesvideos.database.asDomainModel
import com.abdosharaf.devbytesvideos.domain.DomainVideo
import com.abdosharaf.devbytesvideos.network.Network
import com.abdosharaf.devbytesvideos.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideosRepository(private val database: VideosDatabase) {

    val videosList: LiveData<List<DomainVideo>> =
        Transformations.map(database.videoDao.getVideos()) {
            it.asDomainModel()
        }

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            try {
                val playlist = Network.devBytes.getPlaylistAsync().await()
                database.videoDao.insertAll(*playlist.asDatabaseModel())
            } catch (e: Exception) {
                Log.e("TAG", "refreshVideos: Network Error!!!")
            }
        }
    }
}