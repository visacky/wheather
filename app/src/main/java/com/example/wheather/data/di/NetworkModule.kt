package com.example.wheather.data.di

import android.content.Context
import com.example.wheather.extension.hasNetwork
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object NetworkModule {
    private const val BASE_URL = "https://data.gov.sg/api/"

    val retrofitModule = module {
        single { provideRetrofitInstance() }
    }

    private fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(createCache(BaseApplication.appContext))
        .build()

    private fun createCache(context: Context): OkHttpClient {
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        val myCache = Cache(context.cacheDir, cacheSize.toLong())
        return OkHttpClient.Builder()
            .addInterceptor(offlineInterceptor)
            .addNetworkInterceptor(onlineInterceptor)
            .cache(myCache)
            .build()
    }

    private var onlineInterceptor: Interceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = 60 // read from cache for 60 seconds even if there is internet connection
        response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("Pragma")
            .build()
    }

    private var offlineInterceptor: Interceptor = Interceptor { chain ->
        var request = chain.request()
        if (!BaseApplication.appContext.hasNetwork()) {
            val maxStale = 60 * 60 * 24 * 30 // Offline cache available for 30 days
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }
        chain.proceed(request)
    }

}