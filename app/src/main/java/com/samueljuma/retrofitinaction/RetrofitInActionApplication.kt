package com.samueljuma.retrofitinaction

import android.app.Application
import com.samueljuma.retrofitinaction.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RetrofitInActionApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@RetrofitInActionApplication)
            modules(appModules)
        }
    }
}