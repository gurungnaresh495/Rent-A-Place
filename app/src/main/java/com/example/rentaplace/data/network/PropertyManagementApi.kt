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
    suspend fun registerUser(@Body user:User): RegistrationResponse

    @POST("auth/login")
    suspend fun loginUser(@Body user: LoginUser): LoginResponse
}