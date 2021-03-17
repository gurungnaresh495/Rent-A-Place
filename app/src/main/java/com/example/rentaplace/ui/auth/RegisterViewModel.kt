package com.example.rentaplace.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.rentaplace.data.model.LoginResponse
import com.example.rentaplace.data.model.RegistrationResponse
import com.example.rentaplace.data.model.User
import com.example.rentaplace.data.repo.AuthRepository
import com.example.rentaplace.di.component.DaggerAppComponent
import com.example.rentaplace.di.module.AppModule
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class RegisterViewModel: ViewModel() {


    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }


    val registrationStatus = MutableLiveData<RegisterAction>()

    enum class RegisterAction(val message: String){
        SUCCESS("Registration Succeeded"),
        FAILURE("Registration Failed"),
        MISSING_FIELD("Some Fields are missing")
    }

    var email: String? = null
    var password: String? = null
    var type:String? = null
    var name: String? = null

    @Inject
    lateinit var authRepo: AuthRepository

    fun onRegisterButtonClicked(view: View)
    {
        if (email.isNullOrEmpty()|| password.isNullOrEmpty() || type.isNullOrEmpty())
        {
            registrationStatus.value = RegisterAction.MISSING_FIELD
        }
        else
        {
           var response = authRepo.registerUser(User(null, null, email!!, name, password!!, type!!))
            response.subscribeWith(RegistrationObserver())
        }
    }

    inner class RegistrationObserver(): SingleObserver<RegistrationResponse>{
        override fun onSubscribe(d: Disposable) {

        }

        override fun onSuccess(t: RegistrationResponse) {
            registrationStatus.value = if(t.error) RegisterAction.FAILURE else RegisterAction.SUCCESS
        }

        override fun onError(e: Throwable) {
            registrationStatus.value = RegisterAction.FAILURE
            Log.d("abc", e.message.toString())
        }

    }

}