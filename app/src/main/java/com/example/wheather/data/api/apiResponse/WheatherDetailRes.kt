package com.example.wheather.data.api.apiResponse

import com.google.gson.annotations.SerializedName

data class WheatherDetails (
    @SerializedName("dt") var dt : String,
    @SerializedName("main") val main : MainRes,
    @SerializedName("weather") val weather :List<WeatherRes>,
    @SerializedName("clouds") val clouds : CloudRes,
    @SerializedName("wind") val wind : WindRes,
    @SerializedName("sys") val sys : SysRes,
    @SerializedName("dt_txt") val dt_txt : String
)

data class MainRes (
    @SerializedName("temp") var temp : String,
    @SerializedName("temp_min") val temp_min : String,
    @SerializedName("temp_max") val temp_max : String,
    @SerializedName("pressure") val pressure : String,
    @SerializedName("sea_level") val sea_level : String,
    @SerializedName("grnd_level") val grnd_level : String,
    @SerializedName("humidity") val humidity : String,
    @SerializedName("temp_kf") val temp_kf : String
)

data class WeatherRes (
    @SerializedName("id") val id : String,
    @SerializedName("main") val main : String,
    @SerializedName("description") val description : String,
    @SerializedName("icon") val icon : String
)

data class CloudRes (
    @SerializedName("all") val all : String
)

data class WindRes (
    @SerializedName("speed") val speed : String,
    @SerializedName("deg") val deg : String
)

data class SysRes (
    @SerializedName("pod") val pod : String
)