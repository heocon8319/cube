package com.brickmate.cube.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brickmate.cube.AppConstants
import com.brickmate.cube.R
import com.brickmate.cube.utils.TAG
import com.brickmate.cube.utils.addFragment

abstract class BaseActivity : AppCompatActivity(), AppConstants {

    abstract fun layoutId(): Int
    abstract fun fragment(): BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())

        //add main frag
        addFragmentDefault(savedInstanceState)
    }

    private fun addFragmentDefault(savedInstanceState: Bundle?) {
        savedInstanceState ?: addFragment(R.id.clContainer, fragment(), fragment().TAG())
    }

//    override fun onBackPressed() {
//        hideKeyboard()
//        (supportFragmentManager.findFragmentById(R.id.clContainer) as BaseFragment).onBackPressed()
//        super.onBackPressed()
//    }


}