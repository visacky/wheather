package com.example.wheather.domain.usecase

import com.example.wheather.data.api.ApiServiceRepository
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test

class DashboardUseCaseTest {

    private val apiServiceRepository: ApiServiceRepository = mock()
    private val objUseCase = DashboardUseCase(apiServiceRepository)

    @Test
    fun checkDate(){
        val it = objUseCase.convertDateToDay("1570633200")
        assert(it.equals("Wednesday"))
    }
}