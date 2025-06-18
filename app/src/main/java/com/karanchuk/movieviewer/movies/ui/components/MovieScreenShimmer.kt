package com.karanchuk.movieviewer.movies.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.LocalShimmerTheme
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun MovieScreenShimmer(
    modifier: Modifier = Modifier
) {
    val shimmer = rememberShimmer(
        shimmerBounds = ShimmerBounds.View,
        theme = LocalShimmerTheme.current.copy(animationSpec = tween(durationMillis = 600))
    )
    Column(
        modifier = modifier.shimmer(shimmer)
    ) {
        Section()
        Section()
        Section()
        Section()
    }
}

@Composable
private fun Section() {
    Title()
    Column(
        modifier = Modifier.padding(horizontal = 19.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                16.dp,
                alignment = Alignment.CenterHorizontally
            ),
        ) {
            InitCard()
            InitCard()
            InitCard()
            InitCard()
        }
    }
}

@Composable
private fun Title() {
    Box(
        modifier = Modifier
            .padding(top = 24.dp, bottom = 16.dp, start = 19.dp, end = 19.dp)
            .height(20.dp)
            .width(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.Gray)
    )
}

@Composable
private fun InitCard() {
    Column {
        Box(
            modifier = Modifier
                .width(90.dp)
                .height(140.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.Gray)
        )
    }
}

@Preview
@Composable
fun MovieScreenShimmerPreview() {
    MovieScreenShimmer()
}