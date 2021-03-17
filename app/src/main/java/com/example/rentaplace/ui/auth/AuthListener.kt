package com.example.rentaplace.ui.auth

import androidx.lifecycle.MutableLiveData
import com.example.rentaplace.data.model.RegistrationResponse

interface AuthListener {

    fun onSuccess(registerResponse: MutableLiveData<RegistrationResponse>)
    fun onFailure(message: String)
}