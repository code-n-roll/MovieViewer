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
import timber.log.Timber

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
            Timber.tag("karanchuk2").d("feedType=$feedType INITIALIZE: No items in DB for this feed. LAUNCH_INITIAL_REFRESH.")
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            Timber.tag("karanchuk2").d("feedType=$feedType INITIALIZE: Items exist ($itemCount). SKIP_INITIAL_REFRESH.")
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, DbMovie>
    ): MediatorResult {
        return try {
            Timber.tag("karanchuk2").d("feedType=$feedType RemoteMediator.load(type=$loadType, pages=${state.pages.map { it.data.size }}, itemCount=${state.pages.sumOf { it.data.size }})")

            val nextPage = when (loadType) {
                LoadType.REFRESH -> {
                    Timber.tag("karanchuk2").d("feedType=$feedType  → REFRESH (Initial or Full) → nextPage=1")
                    1
                }
                LoadType.PREPEND -> {
                    Timber.tag("karanchuk2").d("feedType=$feedType  → PREPEND → nothing to do")
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        Timber.tag("karanchuk2").d("feedType=$feedType  → APPEND → nothing to do")
                        return MediatorResult.Success(endOfPaginationReached = false)
                    }
                    val remoteKey = db.remoteKeysDao().remoteKeysByMovieId(lastItem.id, feedType)
                    Timber.tag("karanchuk2").d("feedType=$feedType  → APPEND → remoteKey=$remoteKey")
                    val nextKey = remoteKey?.nextKey
                    if (nextKey == null) {
                        Timber.tag("karanchuk2").d("feedType=$feedType  → APPEND → nextKey=null → stopping")
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    Timber.tag("karanchuk2").d("feedType=$feedType  → APPEND → page=$nextKey")
                    nextKey
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
            Timber.tag("karanchuk2").d("feedType=$feedType  → fetched nextPage=$nextPage (size=${movies.size}), total_pages=$totalPages, endOfPaginationReached=$endOfPaginationReached")

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
                Timber.tag("karanchuk2").d("feedType=$feedType dbTransaction: About to insert keys: $keys")
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

            Timber.tag("karanchuk2").d("feedType=$feedType RemoteMediator.load succeeded")
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (t: Throwable) {
            Timber.tag("karanchuk2").e(t, "feedType=$feedType RemoteMediator.load failed")
            MediatorResult.Error(t)
        }
    }
}