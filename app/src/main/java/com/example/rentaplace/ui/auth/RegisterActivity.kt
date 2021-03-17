package com.example.rentaplace.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.rentaplace.R
import com.example.rentaplace.data.model.RegistrationResponse
import com.example.rentaplace.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }


    private fun init() {
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        var viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        registerBinding.registerViewModel = viewModel

        viewModel.registrationStatus.observe(this, Observer {
            if(it == RegisterViewModel.RegisterAction.SUCCESS)
            {
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }
            else
            {
                register_error_message.text = it.message
                register_error_message.visibility = View.VISIBLE
            }
        })
    }



}