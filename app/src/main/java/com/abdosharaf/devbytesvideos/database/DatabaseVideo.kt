package com.abdosharaf.devbytesvideos.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abdosharaf.devbytesvideos.domain.DomainVideo

@Entity(tableName = "videos_table")
data class DatabaseVideo(
    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String
)

fun List<DatabaseVideo>.asDomainModel(): List<DomainVideo> {
    return map {
        DomainVideo(
            url = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}
