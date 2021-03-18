package com.example.rentaplace.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.textservice.SpellCheckerService
import com.example.rentaplace.R
import com.example.rentaplace.helper.SessionManager
import com.example.rentaplace.ui.auth.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }

    private fun init() {
        var sessionManager = SessionManager.getInstance()
        var thread  =  Thread()
        {
            kotlin.run {
                Thread.sleep(3000)
            }
            if (sessionManager.isLoggedIn())
            {
                startActivity(Intent(this, MainActivity::class.java))
            }
            else
            {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }.start()
    }
}