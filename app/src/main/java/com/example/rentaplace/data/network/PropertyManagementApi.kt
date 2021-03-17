package com.example.rentaplace.data.network

import com.example.rentaplace.data.model.LoginResponse
import com.example.rentaplace.data.model.LoginUser
import com.example.rentaplace.data.model.RegistrationResponse
import com.example.rentaplace.data.model.User
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PropertyManagementApi {

    @POST("auth/register")
    fun registerUser(@Body user:User): Single<RegistrationResponse>

    @POST("auth/login")
    fun loginUser(@Body user: LoginUser): Single<LoginResponse>
}