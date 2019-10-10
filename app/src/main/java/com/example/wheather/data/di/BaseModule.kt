package com.example.wheather.data.di


import com.example.wheather.data.api.ApiServiceRepository
import com.example.wheather.data.api.RetrofitEndpoint
import com.example.wheather.domain.usecase.DashboardUseCase
import com.example.wheather.viewmodel.DashboardViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
object BaseModule {

    val mainModule = module {
        factory { provideApiService(get()) }
        factory { ApiServiceRepository(apiService = get()) }
        factory { DashboardUseCase(apiServiceRepo = get()) }
        viewModel{ DashboardViewModel(dataUsageListUseCase = get()) }
    }

    private fun provideApiService(retrofit: Retrofit): RetrofitEndpoint {
        return retrofit.create(RetrofitEndpoint::class.java)
    }

}