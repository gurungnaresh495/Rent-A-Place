package com.example.rentaplace.ui.auth

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class RegistrationPagerAdapter(fragmentManager: FragmentManager) :FragmentPagerAdapter(fragmentManager){

    var fragmentList = ArrayList<Fragment>()
    var titleList = ArrayList<String>()

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(fragment: RegisterFragment)
    {
        fragmentList.add(fragment)
        titleList.add(fragment.fragmentTitle)
    }

}