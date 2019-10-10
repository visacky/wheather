package com.example.wheather.data.api

import com.example.wheather.data.result.Result
import com.example.wheather.constants.NetworkError
import com.example.wheather.data.api.apiResponse.WheatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ApiServiceRepository(private val apiService: RetrofitEndpoint) {
    lateinit var result: Result<WheatherResponse>
    suspend fun getWheatherDetails(): Result<WheatherResponse> {
        withContext(Dispatchers.IO) {
            try {
                val request = apiService.getWheatherDetails()
                val response = request?.await()
                request?.let {
                    if (it.isCompleted) result = Result.success(response)
                    else if (it.isCancelled) result =
                        Result.error(NetworkError.API_ERROR)
                }
            } catch (ex: Exception) {
                result = Result.error(NetworkError.API_ERROR)
            }
        }
        return result
    }

}