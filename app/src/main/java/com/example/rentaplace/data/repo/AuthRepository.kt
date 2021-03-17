package com.example.rentaplace.data.repo

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

import com.example.rentaplace.data.model.RegistrationResponse
import com.example.rentaplace.data.model.User
import com.example.rentaplace.data.network.PropertyManagementApi
import com.example.rentaplace.di.component.DaggerAppComponent
import com.example.rentaplace.di.module.AppModule
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthRepository {
    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

    @Inject
    lateinit var propertyManagementApi: PropertyManagementApi

    @SuppressLint("CheckResult")
    fun registerUser(user: User): MutableLiveData<RegistrationResponse> {
        var registrationResponse = MutableLiveData<RegistrationResponse>()
        propertyManagementApi.registerUser(user).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : SingleObserver<RegistrationResponse> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(t: RegistrationResponse) {
                        registrationResponse.value = t
                    }

                    override fun onError(e: Throwable) {
                        Log.d("abc", e.message.toString())
                    }

                })
        return registrationResponse
    }

}