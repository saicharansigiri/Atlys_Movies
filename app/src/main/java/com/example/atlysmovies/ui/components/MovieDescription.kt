package com.example.atlysmovies.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun MovieDescription(text: String) {
    Text(text = text, fontWeight = FontWeight.W400, fontSize = 16.sp, lineHeight = 24.sp)
}

@Composable
@Preview
fun PreviewMovieDescription() {
    val text = """
        During World War II, Lt. Gen. Leslie Groves Jr. appoints physicist J. Robert Oppenheimer to work on the top-secret Manhattan Project. Oppenheimer and a team of scientists spend years developing and designing the atomic bomb. Their work comes to fruition on July 16, 1945, as they witness the world's first nuclear explosion, forever changing the course of history.
    """.trimIndent()

    MovieDescription(text)
}