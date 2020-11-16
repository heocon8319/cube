package com.brickmate.cube.ui.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import com.brickmate.cube.R
import com.brickmate.cube.sharedPrefs
import com.brickmate.cube.ui.base.BaseActivity
import com.brickmate.cube.ui.main.view.*
import com.brickmate.cube.utils.TAG
import com.brickmate.cube.utils.replaceFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_baby_avatar.*
import kotlinx.android.synthetic.main.layout_action_bar_main.view.*

class MainActivity : BaseActivity() {
    override fun layoutId() = R.layout.activity_main

//    override fun fragment() = TodayFragment.newInstance()
    override fun fragment() = TodayNutriSummaryFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionBar()
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

    private fun initActionBar() {
        val photo = decodeBase64(sharedPrefs.getAvatar())
        Glide.with(this).load(photo)
            .placeholder(R.drawable.ic_baby_avatar_holder)
            .error(R.drawable.ic_baby_avatar_holder)
            .apply(RequestOptions.circleCropTransform())
            .into(abMain.ivAvatar)

        abMain.tvBabyName.text = sharedPrefs.getBabyName()
    }

    private fun decodeBase64(input: String?): Bitmap? {
        val decodedByte: ByteArray = Base64.decode(input, 0)
        return BitmapFactory
            .decodeByteArray(decodedByte, 0, decodedByte.size)
    }
}