package com.example.rentaplace.ui.landlordProperties

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rentaplace.R
import com.example.rentaplace.ui.addProperty.AddPropertyActivity
import kotlinx.android.synthetic.main.activity_landlord_property.*

class LandlordPropertyActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var viewModel: PropertyListViewModel
    lateinit var listAdapter: PropertyListRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landlord_property)
        init()
    }

    private fun init() {
        listAdapter = PropertyListRecyclerAdapter(this)
        landlord_property_recycler_view.adapter = listAdapter
        landlord_property_recycler_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        viewModel = ViewModelProvider(this).get(PropertyListViewModel::class.java)
        viewModel.listOfProperties.observe(this, Observer {
            listAdapter.update(it)
        } )

        add_property_button.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLandlordProperties()
    }

    override fun onClick(v: View?) {
        when (v)
        {
            add_property_button -> {
                startActivity(Intent(this, AddPropertyActivity::class.java))
            }
        }
    }
}