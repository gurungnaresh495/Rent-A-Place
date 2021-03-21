package com.example.rentaplace.data.model

import java.io.Serializable

data class User(

    var email: String? = null,
    var name: String? = null,
    var password: String? = null,
    var type: String? = null,
    var _id: String? = null,
    var createdAt: String? = null
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
