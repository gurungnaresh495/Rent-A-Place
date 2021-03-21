package com.example.rentaplace.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rentaplace.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_register)
        init()
    }


    private fun init() {
        var adapter = RegistrationPagerAdapter(supportFragmentManager)
        registration_view_pager.adapter =adapter

        for ( i in AccountTypes.values())
        {
            adapter.addFragment(RegisterFragment.newInstance(i.name))
        }
        adapter.notifyDataSetChanged()
        registration_tab_layout.setupWithViewPager(registration_view_pager)
    }

}