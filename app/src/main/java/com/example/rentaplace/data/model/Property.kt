package com.example.rentaplace.data.model

data class PropertyResponse(
    val count: Int,
    val data: List<Property>,
    val error: Boolean
)

data class UserPropertyResponse(
    val error: Boolean,
    val data: List<Property>
)

data class Property(
    val _id: String,
    val address: String,
    val city: String,
    val country: String,
    val image: String,
    val latitude: String? = null,
    val longitude: String? = null,
    val mortageInfo: Boolean,
    val propertyStatus: Boolean,
    val purchasePrice: String,
    val state: String,
    val userId: String,
    val userType: String
)