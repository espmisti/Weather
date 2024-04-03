package com.mistbtv.weatherapp.data.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.util.EmptyStackException

internal abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        call: suspend () -> Response<T>,
    ): Flow<Result<T>> = flow {
        try {
            val response = call.invoke()
            val body = response.body()
            if (response.isSuccessful) {
                if (body != null) {
                    emit(Result.success(body))
                } else {
                    emit(Result.failure(EmptyStackException()))
                }
            } else {
                val exception = Exception(response.errorBody()?.toString())
                emit(Result.failure(exception))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}