package com.brickmate.cube.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.brickmate.cube.R
import com.google.android.material.tabs.TabLayout


class TodayMeasureHistoryAdapter(fm: FragmentManager, behavior: Int, private val mContext: Context) : FragmentPagerAdapter(fm, behavior) {

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


    fun getTabView(position: Int): View? {
        val tabTitles = intArrayOf(
            R.string.dialog_measure_history_text_daily,
            R.string.dialog_measure_history_text_weekly,
            R.string.dialog_measure_history_text_monthly
        )
        val image = intArrayOf(R.drawable.bg_tab1_white, R.drawable.bg_tab2_white, R.drawable.bg_tab3_white)
        val v: View = LayoutInflater.from(mContext).inflate(R.layout.layout_custom_tab, null)
        val tvTitle = v.findViewById<View>(R.id.tvTitle) as TextView
        tvTitle.text = mContext.getString(tabTitles[position])
        val clBackground = v.findViewById<View>(R.id.clBackground) as ConstraintLayout
        clBackground.setBackgroundResource(image[position])
        return v
    }

    fun onSelectedView(tabLayout: TabLayout, position: Int) {
        val image = intArrayOf(R.drawable.bg_tab1_green, R.drawable.bg_tab2_green, R.drawable.bg_tab3_green)
        val tab = tabLayout.getTabAt(position)
        val selected: View? = tab!!.customView
        val tvTitle = selected?.findViewById<View>(R.id.tvTitle) as TextView
        tvTitle.setTextColor(mContext.resources.getColor(R.color.white))
        val clBackground = selected?.findViewById<View>(R.id.clBackground) as ConstraintLayout
        clBackground.setBackgroundResource(image[position])
    }

    fun onUnselectedView(tabLayout: TabLayout, position: Int) {
        val image = intArrayOf(R.drawable.bg_tab1_white, R.drawable.bg_tab2_white, R.drawable.bg_tab3_white)
        val tab = tabLayout.getTabAt(position)
        val selected: View? = tab!!.customView
        val tvTitle = selected?.findViewById<View>(R.id.tvTitle) as TextView
        tvTitle.setTextColor(mContext.resources.getColor(R.color.rolling_stone))
        val clBackground = selected?.findViewById<View>(R.id.clBackground) as ConstraintLayout
        clBackground.setBackgroundResource(image[position])
    }
}