package com.example.wheather.domain.usecase

import com.example.wheather.constants.Constants
import com.example.wheather.data.api.ApiServiceRepository
import com.example.wheather.data.api.apiResponse.WheatherDetails
import com.example.wheather.data.api.apiResponse.WheatherResponse
import com.example.wheather.data.result.Result
import com.example.wheather.data.result.ResultTypes
import java.util.*
import java.text.SimpleDateFormat


class DashboardUseCase(private val apiServiceRepo: ApiServiceRepository) {
    suspend fun getWheatherDetails(
        onSuccess: (List<WheatherDetails>?) -> Unit,
        onError: () -> Unit
    ) {
        val response = apiServiceRepo.getWheatherDetails()
        if (response.resultType == ResultTypes.SUCCESS) {
            onSuccess.invoke(prepareData(response))
        } else {
            onError.invoke()
        }
    }

    fun prepareData(response: Result<WheatherResponse>) : List<WheatherDetails>? {
        /*  Split the result data by Year add to to the entity class and sort by the year*/
        response.data?.list?.forEach {
            it.dt = convertDateToDay(it.dt)
            it.main.temp = ((it.main.temp.toDouble() - Constants.CELCIUS)* 9/5 + 32).toString()
        }
        return response.data?.list
    }

    fun convertDateToDay(timestamp : String) : String{
        val mydate = Calendar.getInstance()
        mydate.setTimeInMillis(timestamp.toLong() * 1000)
        return SimpleDateFormat("EEEE").format(mydate.time)
    }
}