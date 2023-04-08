package com.abdosharaf.devbytesvideos.network

import com.abdosharaf.devbytesvideos.database.DatabaseVideo

data class VideosResponse(
    val videos: List<NetworkVideo>
)

data class NetworkVideo(
    val description: String,
    val thumbnail: String,
    val title: String,
    val updated: String,
    val url: String,
    val closedCaptions: String?
)

/*fun VideosResponse.asDomainModel(): List<DomainVideo> {
    return videos.map {
        DomainVideo(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}*/

fun VideosResponse.asDatabaseModel(): Array<DatabaseVideo> {
    return videos.map {
        DatabaseVideo(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }.toTypedArray()
}
