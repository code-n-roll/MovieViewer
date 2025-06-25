package com.karanchuk.movieviewer.repository.movies.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.karanchuk.movieviewer.data.source.local.MovieViewerDatabase
import com.karanchuk.movieviewer.repository.movies.api.MovieApi
import com.karanchuk.movieviewer.repository.movies.apiToDbMovieList
import com.karanchuk.movieviewer.repository.movies.db.model.DbMovie
import com.karanchuk.movieviewer.repository.movies.db.model.DbMovieFeedCrossRef
import com.karanchuk.movieviewer.repository.movies.db.model.DbMovieRemoteKey
import com.karanchuk.movieviewer.repository.movies.db.model.FeedType

@OptIn(ExperimentalPagingApi::class)
class MoviesRemoteMediator(
    private val feedType: FeedType,
    private val movieApi: MovieApi,
    private val db: MovieViewerDatabase,
) : RemoteMediator<Int, DbMovie>() {

    override suspend fun initialize(): InitializeAction {
        // TODO: use 1 hour expiration
        val itemCount = db.moviesDao().getMovieCountForFeed(feedType)
        return if (itemCount == 0) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, DbMovie>
    ): MediatorResult {
        return try {
            val nextPage = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = false)
                    val remoteKey = db.remoteKeysDao().remoteKeysByMovieIdAndFeedType(lastItem.id, feedType)
                    remoteKey?.nextKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            val response = when (feedType) {
                FeedType.POPULAR -> movieApi.getPopularMovies(nextPage)
                FeedType.TOP_RATED -> movieApi.getTopRatedMovies(nextPage)
                FeedType.UPCOMING -> movieApi.getUpcomingMovies(nextPage)
                FeedType.NOW_PLAYING -> movieApi.getNowPlayingMovies(nextPage)
            }

            val movies = response.results.apiToDbMovieList()
            val totalPages = response.totalPages
            val endOfPaginationReached = movies.isEmpty() || nextPage >= totalPages

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.moviesDao().clearFeedCrossRefs(feedType)
                    db.remoteKeysDao().clearRemoteKeysByFeedType(feedType)
                }

                db.moviesDao().insertAll(movies)

                val keys = movies.map { movie ->
                    DbMovieRemoteKey(
                        movieId = movie.id,
                        feedType = feedType,
                        prevKey = if (nextPage > 1) nextPage - 1 else null,
                        nextKey = if (!endOfPaginationReached) nextPage + 1 else null
                    )
                }
                db.remoteKeysDao().insertAll(keys)

                val startPosition = ((nextPage - 1) * state.config.pageSize)
                val crossRefs = movies.mapIndexed { index, dbMovie ->
                    DbMovieFeedCrossRef(
                        feedType = feedType,
                        movieId = dbMovie.id,
                        position = startPosition + index
                    )
                }
                db.moviesDao().insertFeedCrossRefs(crossRefs)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (t: Throwable) {
            MediatorResult.Error(t)
        }
    }
}