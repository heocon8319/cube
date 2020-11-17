package com.brickmate.cube.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TodayMeasureHistoryAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {

    var mFragmentCollection: MutableList<Fragment> = ArrayList()
    var mTitleCollection: MutableList<String> = ArrayList()

    override fun getCount(): Int {
        return mFragmentCollection.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentCollection[position]
    }

    fun addFragment(title: String, fragment: Fragment) {
        mTitleCollection.add(title)
        mFragmentCollection.add(fragment)
    }

    //Needed for
    override fun getPageTitle(position: Int): CharSequence? {
        return mTitleCollection[position]
    }
}