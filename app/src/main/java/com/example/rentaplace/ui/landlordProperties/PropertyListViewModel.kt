package com.example.rentaplace.ui.landlordProperties

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentaplace.data.model.Property
import com.example.rentaplace.data.network.PropertyManagementApi
import com.example.rentaplace.di.component.DaggerAppComponent
import com.example.rentaplace.di.module.AppModule
import com.example.rentaplace.helper.SessionManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class PropertyListViewModel:ViewModel() {

    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

    var listOfProperties = MutableLiveData<ArrayList<Property>>()

    @Inject
    lateinit var api: PropertyManagementApi

    @Inject
    lateinit var sessionManager: SessionManager

    fun getLandlordProperties()
    {
        viewModelScope.launch {
            listOfProperties.value = api.getUserProperties(sessionManager.getUserId()!!).data as ArrayList<Property>
        }
    }
}