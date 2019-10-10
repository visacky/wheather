package com.example.wheather.data.api.apiResponse

import com.google.gson.annotations.SerializedName

data class CityDetailRes (
    @SerializedName("id") val id : String,
    @SerializedName("name") val name : MainRes,
    @SerializedName("country") val country : WeatherRes,
    @SerializedName("population") val population : CloudRes,
    @SerializedName("timezone") val timezone : WindRes,
    @SerializedName("sunrise") val sunrise : SysRes,
    @SerializedName("sunset") val sunset : String
    )