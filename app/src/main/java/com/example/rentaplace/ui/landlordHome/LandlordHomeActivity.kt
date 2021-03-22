package com.example.rentaplace.ui.landlordHome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide.init
import com.example.rentaplace.R
import com.example.rentaplace.ui.landlordProperties.LandlordPropertyActivity
import kotlinx.android.synthetic.main.activity_landlord_home.*

class LandlordHomeActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landlord_home)
        init()
    }

    private fun init() {
        landlord_home_properties_layout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v)
        {
            landlord_home_properties_layout ->
            {
                startActivity(Intent(this, LandlordPropertyActivity::class.java))
            }
        }
    }
}