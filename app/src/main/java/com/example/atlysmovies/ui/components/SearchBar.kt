package com.example.atlysmovies.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atlysmovies.R

@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }
    Surface(
        shape = RoundedCornerShape(8),
        color = MaterialTheme.colorScheme.surfaceVariant, // Customize color if needed
        modifier = Modifier
            .fillMaxWidth().padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = Color(0xFF9AA4B2)
            )
            TextField(
                value = text,
                onValueChange = {

                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        "Search movies",
                        fontWeight = W400,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = Color(0xFF9AA4B2)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
@Preview
fun PreviewSearchBar() {
    SearchBar()
}