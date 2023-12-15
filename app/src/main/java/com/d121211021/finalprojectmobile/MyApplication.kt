package com.d121211021.finalprojectmobile

import android.app.Application
import com.d121211021.finalprojectmobile.data.AppContainer
import com.d121211021.finalprojectmobile.data.DefaultAppContainer

class MyApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}