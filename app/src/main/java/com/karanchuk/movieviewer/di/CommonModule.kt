package com.karanchuk.movieviewer.di

import android.content.Context
import coil.ImageLoader
import com.karanchuk.common.ui.ImagePrefetcher
import com.karanchuk.common.ui.ImagePrefetcherImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Singleton
    @Provides
    fun providesImageLoader(@ApplicationContext appContext: Context): ImageLoader {
        return ImageLoader.Builder(appContext)
            .crossfade(true)
            .build()
    }

    @Singleton
    @Provides
    fun providesImagePrefetcher(
        imageLoader: ImageLoader,
        @ApplicationContext appContext: Context,
    ): ImagePrefetcher = ImagePrefetcherImpl(imageLoader, appContext)
}