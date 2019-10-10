package com.example.wheather.data.di

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        initKoin()
    }
    private fun initKoin() {
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    BaseModule.mainModule,
                    NetworkModule.retrofitModule
                )
            )
        }
    }
    companion object {
        lateinit  var appContext: Context
    }

}