package com.techne.jenotes.data.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.techne.jenotes.data.model.ErrorMessage
import com.techne.jenotes.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteDataAccess @Inject constructor(
    private val errorAdapter: JsonAdapter<ErrorMessage>
) {

    fun <T : Any> remoteCall(call: suspend () -> Response<T>): Flow<Resource<T>> = flow {
        emit(Resource.Loading())
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(Resource.Success(body))
                } else {
                    emit(Resource.Error(code = response.code(), error = "Empty response body"))
                }
            } else {
                val errorBody = response.errorBody()?.string().orEmpty()
                val errorDetails = parseErrorBody(errorBody)
                emit(Resource.Error(code = response.code(), error = errorDetails))
            }
        } catch (e: Exception) {
            emit(Resource.Exception(e))
        }
    }

    private fun parseErrorBody(errorBody: String): String {
        if (errorBody.isEmpty()) {
            return "Empty error response"
        }
        return try {
            val errorMessage = errorAdapter.fromJson(errorBody)
            errorMessage?.getFormattedMessage() ?: "Unknown error"
        } catch (e: JsonDataException) {
            "Malformed error response: ${e.message}"
        } catch (e: IOException) {
            "Error reading the response: ${e.message}"
        } catch (e: Exception) {
            "Unexpected error occurred: ${e.message}"
        }
    }
}
