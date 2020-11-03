package com.brickmate.cube.ui.main

import android.os.Bundle
import com.brickmate.cube.R
import com.brickmate.cube.ui.base.BaseActivity
import com.brickmate.cube.ui.main.view.*
import com.brickmate.cube.utils.TAG
import com.brickmate.cube.utils.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun layoutId() = R.layout.activity_main

    override fun fragment() = TodayFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavigationBar()
    }

    private fun initNavigationBar() {
        bnvNavigation.itemIconTintList = null
        bnvNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_today -> {
                    val fragDes = TodayFragment.newInstance()
                    replaceFragment(R.id.clContainer, fragDes, fragDes.TAG())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_recipe -> {
                    val fragDes = RecipeFragment.newInstance()
                    replaceFragment(R.id.clContainer, fragDes, fragDes.TAG())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_add -> {
                    val fragDes = AddMealFragment.newInstance()
                    replaceFragment(R.id.clContainer, fragDes, fragDes.TAG())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_shop -> {
                    val fragDes = ShopFragment.newInstance()
                    replaceFragment(R.id.clContainer, fragDes, fragDes.TAG())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_community -> {
                    val fragDes = CommunityFragment.newInstance()
                    replaceFragment(R.id.clContainer, fragDes, fragDes.TAG())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}