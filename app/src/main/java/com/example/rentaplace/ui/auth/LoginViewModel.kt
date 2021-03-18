package com.example.rentaplace.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentaplace.data.model.LoginResponse
import com.example.rentaplace.data.model.LoginUser
import com.example.rentaplace.data.network.PropertyManagementApi
import com.example.rentaplace.data.repo.AuthRepository
import com.example.rentaplace.di.component.DaggerAppComponent
import com.example.rentaplace.di.module.AppModule
import com.example.rentaplace.helper.SessionManager
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel: ViewModel() {

    val loginStatus = MutableLiveData<LoginAction>()

    var email: String? = null
    var password: String? = null

    var sessionManager = SessionManager.getInstance()

    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

    @Inject
    lateinit var propertyManagementApi: PropertyManagementApi

    private val coroutineExceptionHanlder = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
        loginStatus.value = LoginAction.FAILURE
    }

    enum class LoginAction(val message: String){
        SUCCESS("Login Succeeded"),
        FAILURE("Login Failed"),
        MISSING_FIELD("Some Fields are missing")
    }

    fun onLoginButtonClicked(view: View)
    {
        if (email.isNullOrEmpty()|| password.isNullOrEmpty())
        {
            loginStatus.value = LoginAction.MISSING_FIELD
        }
        else
        {
            viewModelScope.launch(Dispatchers.Main + coroutineExceptionHanlder) {
                propertyManagementApi.loginUser(LoginUser(email!!, password!!))
                loginStatus.value = LoginAction.SUCCESS
            }
        }
    }


}