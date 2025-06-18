package com.karanchuk.movieviewer.repository.movies

import com.karanchuk.movieviewer.common.ImagePrefetcher
import com.karanchuk.movieviewer.repository.movies.db.dao.MoviesDao
import com.karanchuk.movieviewer.repository.movies.db.model.DbMovieFeedCrossRef
import com.karanchuk.movieviewer.repository.movies.db.model.FeedType
import com.karanchuk.movieviewer.repository.movies.api.MovieApi
import com.karanchuk.movieviewer.di.DefaultDispatcher
import com.karanchuk.movieviewer.di.IoDispatcher
import com.karanchuk.movieviewer.repository.movies.domain.Movie
import com.karanchuk.movieviewer.repository.movies.domain.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val networkDataSource: MovieApi,
    private val localDataSource: MoviesDao,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val imagePrefetcher: ImagePrefetcher,
) : MoviesRepository {

    override suspend fun loadMovies(feedType: FeedType) {
        withContext(ioDispatcher) {
            runCatching {
                when (feedType) {
                    FeedType.POPULAR -> networkDataSource.getPopularMovies()
                    FeedType.TOP_RATED -> networkDataSource.getTopRatedMovies()
                    FeedType.UPCOMING -> networkDataSource.getUpcomingMovies()
                    FeedType.NOW_PLAYING -> networkDataSource.getNowPlayingMovies()
                }.results
            }
                .mapCatching { apiMovies -> apiMovies.apiToDbMovieList() }
                .onSuccess { dbMovies ->
                    localDataSource.insertAll(dbMovies)

                    val crossRefs = dbMovies.mapIndexed { index, dbMovie ->
                        DbMovieFeedCrossRef(
                            feedType = feedType,
                            movieId = dbMovie.id,
                            position = index
                        )
                    }
                    localDataSource.insertFeedCrossRefs(crossRefs)

                    dbMovies.forEach { dbMovie ->
                        imagePrefetcher.prefetchImage(url = dbMovie.posterUrl)
                    }
                }
        }
    }

    override fun getMoviesFlow(feedType: FeedType): Flow<List<Movie>> {
        return localDataSource.observeAll(feedType = feedType).map { dbMovies ->
            withContext(defaultDispatcher) {
                dbMovies.toMovieList()
            }
        }
    }
}