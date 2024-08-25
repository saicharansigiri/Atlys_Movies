package com.example.atlysmovies.errorutils

import android.util.Log
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object ErrorHandler {

    fun handleException(exception: Exception): ErrorResponse {
        return when (exception) {
            is HttpException -> {
                val code = exception.code()
                Log.e("ErrorHandler", "HTTP error: $code ${exception.message()}")
                ErrorResponse(
                    errorMsg = "An error occurred. Please try again later.",
                    retry = true
                )
            }

            is SocketTimeoutException -> {
                Log.e("ErrorHandler", "Timeout error: ${exception.message}")
                ErrorResponse(
                    errorMsg = "Request timed out. Please check your internet connection and try again.",
                    retry = true
                )
            }

            is IOException -> {
                Log.e("ErrorHandler", "Network error: ${exception.message}")
                ErrorResponse(
                    errorMsg = "Network error. Please check your connection and try again.",
                    retry = true
                )
            }

            else -> {
                Log.e("ErrorHandler", "Unknown error: ${exception.message}")
                ErrorResponse(
                    errorMsg = "An unexpected error occurred. Please try again.",
                    retry = true
                )
            }
        }
    }
}

data class ErrorResponse(
    val errorMsg: String,
    val retry: Boolean
)
