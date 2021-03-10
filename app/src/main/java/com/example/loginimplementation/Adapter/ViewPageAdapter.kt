package com.example.mainpage.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPageAdapter(supportFragmentManager: FragmentManager): FragmentPagerAdapter(supportFragmentManager) {

    // one value hold fragment , the other old the title
    private val mFragmentList= ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getCount(): Int { //retuirn size of arraylist
        return mFragmentList.size
    }

    override fun getItem(position: Int): Fragment { //return the position of a fragment
        return mFragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {  //get the title in the arraytitle
        return mFragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title:String){  //create a fragment and its title
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

}