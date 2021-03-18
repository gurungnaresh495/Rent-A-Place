package com.example.rentaplace.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rentaplace.ui.home.MainActivity
import com.example.rentaplace.R
import com.example.rentaplace.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        var viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginBinding.loginViewModel = viewModel

        viewModel.loginStatus.observe(this, Observer {
            if(it == LoginViewModel.LoginAction.SUCCESS)
            {
                Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
            else
            {
                login_error_message.text = it.message
                login_error_message.visibility = View.VISIBLE
            }
        })
    }
}