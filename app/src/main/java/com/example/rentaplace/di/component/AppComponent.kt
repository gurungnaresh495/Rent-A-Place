package com.example.rentaplace.di.component

import com.example.rentaplace.data.repo.AuthRepository
import com.example.rentaplace.di.module.AppModule
import com.example.rentaplace.ui.auth.RegisterViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(authClass: AuthRepository)
    fun inject(viewModel: RegisterViewModel)
}