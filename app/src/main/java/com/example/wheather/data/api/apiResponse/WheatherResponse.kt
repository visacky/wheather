package com.example.wheather.data.api.apiResponse

import com.google.gson.annotations.SerializedName

data class WheatherResponse (
    @SerializedName("cod") val cod : String,
    @SerializedName("message") val message : String,
    @SerializedName("cnt") val cnt : String,
    @SerializedName("list") val list : List<WheatherDetails>

//    @SerializedName("city") val result : CityDetailRes
)