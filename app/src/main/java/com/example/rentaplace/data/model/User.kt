package com.example.rentaplace.data.model

import java.io.Serializable

data class User(
    val _id: String?,
    val createdAt: String?,
    val email: String,
    val name: String?,
    val password: String,
    val type: String
) : Serializable

data class RegistrationResponse(
    val `data`: User,
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
