package com.karanchuk.movieviewer.movies.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun MovieCard(
    state: MovieCardState,
    onItemClick: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(state.id) })
        ,
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.posterUrl)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .height(96.dp)
                    .width(64.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = state.title,
                    modifier = Modifier,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                )
                Text(
                    text = state.date,
                    modifier = Modifier
                )
                Text(
                    text = state.vote,
                    modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            // TODO: restore favorite button
//            IconButton(
//                modifier = Modifier
//                    .align(Alignment.CenterVertically),
//                onClick = { onFavoriteClick(state.id) }
//            ) {
//                Icon(
//                    modifier = Modifier,
//                    tint = androidx.compose.ui.graphics.Color.Red,
//                    imageVector = Icons.Default.FavoriteBorder,
//                    contentDescription = null,
//                )
//            }
        }
    }
}