package com.example.rentaplace.data.model

import java.io.Serializable

data class User(

    val email: String,
    val name: String?,
    val password: String,
    val type: String,
    val _id: String? = null,
    val createdAt: String? = null
) : Serializable

data class RegistrationResponse(
    val data: User? = null,
    val error: Boolean,
    val message: String
)

data class LoginResponse(
    val token: String,
    val user: User
)

data class LoginUser
    (
    val email: String,
    val password: String
)
