package com.karanchuk.movieviewer.common

import android.content.Context
import coil.ImageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import javax.inject.Inject
import javax.inject.Singleton

interface ImagePrefetcher {
    fun prefetchImage(url: String)
}

@Singleton
class ImagePrefetcherImpl @Inject constructor(
    private val imageLoader: ImageLoader,
    private val appContext: Context,
) : ImagePrefetcher {

    override fun prefetchImage(url: String) {
        val request = ImageRequest.Builder(appContext)
            .data(url)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build()

        imageLoader.enqueue(request)
    }
}