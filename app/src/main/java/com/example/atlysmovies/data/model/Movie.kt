package com.example.atlysmovies.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Movie(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Description")
    val description: String?
) : Parcelable