package com.example.rentaplace.di.module

import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.rentaplace.MyApplication
import com.example.rentaplace.app.Config
import com.example.rentaplace.data.network.PropertyManagementApi
import com.example.rentaplace.data.repo.AuthRepository
import com.example.rentaplace.helper.SessionManager
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {



    @Provides
    fun getRetrofit(): PropertyManagementApi {
        return Retrofit.Builder().baseUrl(Config.BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(PropertyManagementApi::class.java)
    }

    @Provides
    fun getSessionManager(): SessionManager
    {
        return SessionManager.getInstance()
    }

    @Provides
    fun getAuthRepo(): AuthRepository
    {
        return AuthRepository()
    }

    @Provides
    fun getGlideInstance(): RequestManager
    {
        return Glide.with(MyApplication.appContext)
    }
}