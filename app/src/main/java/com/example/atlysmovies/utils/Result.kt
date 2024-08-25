package com.example.atlysmovies.utils

import com.example.atlysmovies.errorutils.ErrorResponse


sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val errorResponse: ErrorResponse) : Result<Nothing>()
}
