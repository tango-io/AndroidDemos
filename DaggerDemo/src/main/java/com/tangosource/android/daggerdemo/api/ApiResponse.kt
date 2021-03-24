package com.tangosource.android.daggerdemo.api

import com.squareup.moshi.JsonClass
import com.tangosource.android.daggerdemo.di.modules.MoshiModule
import com.tangosource.android.daggerdemo.utils.fromJson
import retrofit2.HttpException

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    val error: Boolean,
    val error_type: String,
    val system_message: String,
    val friendly_message: String,
    val result: String,
    val data: Data
)

@JsonClass(generateAdapter = true)
data class Data(val msg: String?)
sealed class ApiResponse<out T> {
    class Success<T>(val data: T) : ApiResponse<T>()
    class Error<T>(val errorMes: String) : ApiResponse<T>()
}

suspend fun <T> safeAPICall(apiCall: suspend () -> T): ApiResponse<T> {
    return try {
        val response = apiCall()
        ApiResponse.Success(response)
    } catch (e: Exception) {
        when (e) {
            is HttpException -> e.parseException()
            else -> ApiResponse.Error<Nothing>(e.message ?: "")
        }
    }
}

private fun HttpException.parseException(): ApiResponse.Error<Nothing> {
    val errorBody = response()?.errorBody()?.string() ?: ""
    return if (errorBody.isNotEmpty()) {
        val moshi = MoshiModule().baseMoshi()
        try {
            val response = moshi.fromJson<ErrorResponse>(errorBody)
            ApiResponse.Error<Nothing>(response?.friendly_message ?: message())
        } catch (e: java.lang.Exception) {
            ApiResponse.Error<Nothing>("Unable to parse response")
        }
    } else {
        ApiResponse.Error(message())
    }
}