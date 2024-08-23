package com.example.atlysmovies.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesListResponse(
    @SerializedName("Search") val list: List<Movie>,
    @SerializedName("totalResults") val totalResults: String,
    @SerializedName("Response") val response: String,
) : Parcelable
