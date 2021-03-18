package com.example.rentaplace

import android.app.Application
import android.content.Context
import com.example.rentaplace.di.component.AppComponent
import com.example.rentaplace.di.component.DaggerAppComponent
import com.example.rentaplace.di.module.AppModule

class MyApplication: Application() {

    lateinit var daggerAppComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        daggerAppComponent = DaggerAppComponent.builder().appModule(AppModule()).build()
    }

    companion object
    {
        lateinit var appContext: Context
    }
}