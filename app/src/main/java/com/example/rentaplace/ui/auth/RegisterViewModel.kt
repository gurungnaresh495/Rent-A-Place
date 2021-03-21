package com.example.rentaplace.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentaplace.data.model.RegisterError
import com.example.rentaplace.data.model.User
import com.example.rentaplace.data.network.PropertyManagementApi
import com.example.rentaplace.di.component.DaggerAppComponent
import com.example.rentaplace.di.module.AppModule
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


class RegisterViewModel: ViewModel() {

    var user: User = User()
    var confirmPassword: String? = null
    var errorMessage = MutableLiveData<String>().apply { value = "" }

    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
        //giving default value for type
        user.type = AccountTypes.Tenant.name.toLowerCase()
    }

    val registrationStatus = MutableLiveData<RegisterAction>()

    enum class RegisterAction(val message: String){
        SUCCESS("Registration Succeeded"),
        FAILURE("Registration Failed"),
        PASSWORD_MATCH("Two passwords do not match"),
        MISSING_FIELD("Some Fields are missing")
    }



    @Inject
    lateinit var propertyManagementApi: PropertyManagementApi

    fun onRegisterButtonClicked(view: View)
    {
        if (user.email.isNullOrEmpty()|| user.password.isNullOrEmpty()  ||
                confirmPassword.isNullOrEmpty())
        {
            registrationStatus.value = RegisterAction.MISSING_FIELD
            errorMessage.value = RegisterAction.MISSING_FIELD.message
        }
        else if(user.password != confirmPassword)
        {
            registrationStatus.value = RegisterAction.PASSWORD_MATCH
            errorMessage.value = RegisterAction.PASSWORD_MATCH.message
        }
        else
        {
            viewModelScope.launch {
                try{
                    propertyManagementApi.registerUser(User( user.email, user.name, user.password, user.type))
                    registrationStatus.value = RegisterAction.SUCCESS
                }
                catch (e: HttpException)
                {
                    registrationStatus.value = RegisterAction.FAILURE
                    var errorResponse = Gson().fromJson(e.response()!!.errorBody()!!.string(), RegisterError::class.java )
                    errorMessage.value = errorResponse.message.replace(',', '\n')
                }
            }
        }
    }

}