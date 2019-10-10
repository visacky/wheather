package com.example.wheather.data.api

import com.example.wheather.data.api.apiResponse.WheatherResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface RetrofitEndpoint {
    @GET("http://api.openweathermap.org/data/2.5/forecast?q=singapore,sg&APPID=8903395650a1a8a180d45ac717aadaad&cnt=5")
    fun getWheatherDetails(): Deferred<WheatherResponse>
}