package com.karanchuk.repository.movies

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.karanchuk.core.db.MovieViewerDatabase
import com.karanchuk.repository.movies.api.MovieApi
import com.karanchuk.common.model.domain.FeedType
import com.karanchuk.common.model.domain.Movie
import com.karanchuk.repository.movies.domain.MoviesRepository
import com.karanchuk.repository.movies.paging.MoviesRemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val PAGE_SIZE = 20

@Singleton
internal class MoviesRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val db: MovieViewerDatabase,
) : MoviesRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagedMoviesFlow(feedType: FeedType): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
            ),
            remoteMediator = MoviesRemoteMediator(
                feedType = feedType,
                movieApi = api,
                db = db,
            ),
            pagingSourceFactory = { db.moviesDao().pagingSource(feedType) }
        ).flow
            .map { pagingData -> pagingData.map { it.toMovie() } }
    }
}