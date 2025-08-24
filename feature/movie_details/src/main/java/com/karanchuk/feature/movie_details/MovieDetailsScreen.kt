package com.karanchuk.feature.movie_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Immutable
data class Header(
    val imageUrl: String,
    val title: String,
    val genres: List<String>,
    val description: String,
    val voteAverage: Float,
    val voteCount: Int,
) {
    companion object {
        val Preview = Header(
            imageUrl = "",
            title = "Lilo & Stitch",
            genres = listOf("Family", "Science Fiction", "Comedy", "Adventure"),
            description = "The wildly funny and touching story of a lonely Hawaiian girl and the fugitive alien who helps to mend her broken family.",
            voteAverage = 7.1f,
            voteCount = 698,
        )
    }
}

data class Cast(
    val imageUrl: String,
    val actorName: String,
    val characterName: String,
)

data class Video(
    val imageUrl: String,
)

data class Recommended(
    val imageUrl: String,
    val title: String,
    val genres: List<String>,
)

data class Similar(
    val imageUrl: String,
    val title: String,
    val genres: List<String>,
)

data class Information(
    val releaseDate: String,
    val language: String,
    val budget: String,
    val revenue: String,
    val productionCompanies: String,
)

@Immutable
data class Content(
    val header: Header,
    val cast: List<Cast>,
    val videos: List<Video>,
) {
    companion object {
        val Preview = Content(
            header = Header.Preview,
            cast = listOf(),
            videos = listOf(),
        )
    }
}

data class MovieDetailsUiState(
    val topBarState: TopBarState,
    val content: Content,
    val error: Boolean,
    val isLoading: Boolean,
) {
    companion object {
        val Preview = MovieDetailsUiState(
            topBarState = TopBarState.Preview,
            content = Content.Preview,
            error = false,
            isLoading = false,
        )
    }
}

//sealed interface MovieDetailsUiState {
//    data object Loading : MovieDetailsUiState
//    data object Error : MovieDetailsUiState
//    data class Content(
//        val header: Header,
//        val cast: List<Cast>,
//        val videos: List<Video>,
//    ) : MovieDetailsUiState
//
//    companion object {
//        val Preview = Content(
//            header = Header.Preview,
//            cast = listOf(),
//            videos = listOf(),
//        )
//    }
//}

data class TopBarState(
    val isFavorite: Boolean,
    val title: String,
) {
    companion object {
        val Preview = TopBarState(
            isFavorite = false,
            title = "",
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    onBackClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    state: MovieDetailsUiState = MovieDetailsUiState.Preview,
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onFavoriteClick) {
                        Icon(
                            imageVector = if (state.topBarState.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = Color.Red,
                        )
                    }
                },
                title = { Text(text = state.topBarState.title) },
                colors = TopAppBarDefaults.topAppBarColors(),
                windowInsets = WindowInsets(0.dp)
            )
        }
    ) { paddingValues ->
        when {
            state.error -> Error()
            state.isLoading -> Loading()
            else -> Content(
                state = state.content,
                paddingValues = paddingValues
            )
        }
    }
}

@Composable
private fun Error() {

}

@Composable
private fun Loading() {

}

@Composable
private fun Content(
    state: Content,
    paddingValues: PaddingValues,
) {
    Column() {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(state.header.imageUrl)
                .build(),
            contentScale = ContentScale.FillHeight,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Row() {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.header.imageUrl)
                    .build(),
                contentScale = ContentScale.FillHeight,
                contentDescription = null,
                modifier = Modifier
                    .width(50.dp)
                    .height(100.dp)
            )
            Column() {
                Text(text = state.header.title)
                Genres(state = state.header.genres)
                Text(text = state.header.description)
            }
        }
    }
}

@Composable
fun Genres(
    state: List<String>
) {
    Row() {
        state.forEach {
            Text(text = it)
        }
    }
}

@Composable
@Preview
fun MovieDetailsScreenSuccessPreview() {
    MovieDetailsScreen(
        state = MovieDetailsUiState.Preview,
    )
}