package com.example.rentaplace.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rentaplace.data.model.LoginResponse
import com.example.rentaplace.data.model.LoginUser
import com.example.rentaplace.data.repo.AuthRepository
import com.example.rentaplace.di.component.DaggerAppComponent
import com.example.rentaplace.di.module.AppModule
import com.example.rentaplace.helper.SessionManager
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
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
    lateinit var authRepo: AuthRepository

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
            var response = authRepo.loginUser(LoginUser( email!!, password!!))
            response.subscribeWith(LoginObserver())
        }
    }



    inner class LoginObserver(): SingleObserver<LoginResponse>
    {
        override fun onSubscribe(d: Disposable) {

        }

        override fun onSuccess(t: LoginResponse) {
            loginStatus.value = LoginAction.SUCCESS
            sessionManager.register(t.user._id!!, t.user.name!!, t.user.email,t.user.password)
        }

        override fun onError(e: Throwable) {
            loginStatus.value = LoginAction.FAILURE
            Log.d("abc", e.message.toString())
        }

    }
}