package com.karanchuk.movieviewer.feature.movies.ui.components.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun MovieCard(
    state: MovieCardState,
    onItemClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(state.posterUrl)
                .build(),
            contentScale = ContentScale.FillHeight,
            contentDescription = null,
            modifier = Modifier
                .height(140.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable { onItemClick(state.id) }
        )
        Text(
            text = state.title,
            modifier = Modifier
                .width(100.dp)
                .padding(top = 8.dp)
            ,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            ),
            maxLines = 2,
            minLines = 2,
            overflow = TextOverflow.Visible,
        )
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    MovieCard(
        state = MovieCardState.Preview,
        onItemClick = {},
    )
}