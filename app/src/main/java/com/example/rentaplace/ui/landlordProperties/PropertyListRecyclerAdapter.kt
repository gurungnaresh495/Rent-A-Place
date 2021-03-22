package com.example.rentaplace.ui.landlordProperties

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.rentaplace.R
import com.example.rentaplace.data.model.Property
import com.example.rentaplace.di.component.DaggerAppComponent
import com.example.rentaplace.di.module.AppModule
import kotlinx.android.synthetic.main.row_layout_property.view.*
import javax.inject.Inject

class PropertyListRecyclerAdapter(var context: Context): RecyclerView.Adapter<PropertyListRecyclerAdapter.LandlordListViewHolder>() {

    private var propertyList = ArrayList<Property>()
    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

    @Inject
    lateinit var glide: RequestManager

    inner class LandlordListViewHolder(var view: View): RecyclerView.ViewHolder(view)
    {
        fun bind(property:Property)
        {
            view.row_layout_property_text_view.text = property.address
            glide.load(property.image).into(view.row_layout_property_image_view)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandlordListViewHolder {
        return LandlordListViewHolder(LayoutInflater.from(context).inflate(R.layout.row_layout_property, parent, false))
    }

    override fun onBindViewHolder(holder: LandlordListViewHolder, position: Int) {
        holder.bind(propertyList[position])
    }

    override fun getItemCount(): Int {
        return propertyList.size
    }

    fun update(list: ArrayList<Property>)
    {
        this.propertyList = list
        notifyDataSetChanged()
    }

}