package com.example.wheather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheather.data.api.apiResponse.WheatherDetails
import com.example.wheather.domain.usecase.DashboardUseCase
import kotlinx.coroutines.launch

class DashboardViewModel(private val dataUsageListUseCase: DashboardUseCase) : ViewModel() {

    val isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var wheatherList: List<WheatherDetails>? = listOf()

    init {
        initAPIService()
    }

    private fun initAPIService() {
        viewModelScope.launch {
            isLoadingLiveData.postValue(true)
            dataUsageListUseCase.getWheatherDetails({
                wheatherList = it
                isLoadingLiveData.postValue(false)
            }, {
                isLoadingLiveData.postValue(false)
                /*Handle Error*/
            })
        }
    }

    fun convertData(wheatherDetails: WheatherDetails?): String {
        return if (wheatherDetails != null)
            wheatherDetails.main.temp + "°"
        else
            ""
    }

}