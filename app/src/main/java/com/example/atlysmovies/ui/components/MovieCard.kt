package com.example.atlysmovies.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.atlysmovies.R

@Composable
fun MovieCard() {
    Column(horizontalAlignment = Alignment.Start) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
        )
        {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://www.omdbapi.com/src/poster.jpg")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.error),
                contentDescription = stringResource(R.string.description),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Doctor Strange",
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    }
}


@Composable
@Preview
fun PreviewMovieCard() {
    MovieCard()
}