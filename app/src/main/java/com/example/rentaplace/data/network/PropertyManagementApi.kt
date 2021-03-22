package com.example.rentaplace.data.network

import com.example.rentaplace.data.model.*
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PropertyManagementApi {

    @POST("auth/register")
    suspend fun registerUser(@Body user:User): RegistrationResponse

    @POST("auth/login")
    suspend fun loginUser(@Body user: LoginUser): LoginResponse

    @GET("property/user/{userId}")
    suspend fun getUserProperties(@Path("userId") userId: String): UserPropertyResponse
}