package com.abdosharaf.devbytesvideos.domain

import android.net.Uri
import com.abdosharaf.devbytesvideos.utils.smartTruncate

data class DomainVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String
) {

    val shortDescription: String
        get() = description.smartTruncate(200)

    val launchUri: Uri
        get() {
            val httpUri = Uri.parse(url)
            return Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))
        }
}
