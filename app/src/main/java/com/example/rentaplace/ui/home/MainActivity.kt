package com.example.rentaplace.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rentaplace.R
import com.example.rentaplace.helper.SessionManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text_view_main.text=SessionManager.getInstance().getUserName()
    }
}