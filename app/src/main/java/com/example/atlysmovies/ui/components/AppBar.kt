package com.example.atlysmovies.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.atlysmovies.R

@Composable
fun AppBar(onBackClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = { onBackClick.invoke() }) {
            Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = null,
                modifier = Modifier.fillMaxSize())
        }
    }
}


@Composable
@Preview
fun PreviewAppBar() {
    AppBar() {}
}