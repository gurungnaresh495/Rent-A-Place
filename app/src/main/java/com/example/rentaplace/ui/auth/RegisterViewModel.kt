package com.example.rentaplace.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.rentaplace.data.model.User
import com.example.rentaplace.data.repo.AuthRepository
import com.example.rentaplace.di.component.DaggerAppComponent
import com.example.rentaplace.di.module.AppModule
import javax.inject.Inject

class RegisterViewModel: ViewModel() {

    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

    lateinit var listener: AuthListener

    var email: String? = null
    var password: String? = null
    var type:String? = null
    var name: String? = null

    @Inject
    lateinit var authRepo: AuthRepository


    fun onRegisterButtonClicked(view: View)
    {
        if (email.isNullOrEmpty() || password.isNullOrEmpty() || type.isNullOrEmpty())
        {
            var errorMessage = if(email.isNullOrEmpty()) "Invalid email"
            else if (password.isNullOrEmpty()) "Invalid password"
            else if (type.isNullOrEmpty()) "Invalid Type"
            else "Invalid email or password or type"
            errorMessage += ". Try again!"
            listener.onFailure(errorMessage)
        }
        else
        {
             listener.onSuccess(authRepo.registerUser(User(null, null, email!!, name, password!!, type!!)))
        }
    }
}