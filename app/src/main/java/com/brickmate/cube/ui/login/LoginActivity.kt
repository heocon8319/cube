package com.brickmate.cube.ui.login

import android.os.Bundle
import android.widget.ImageView
import com.brickmate.cube.R
import com.brickmate.cube.ui.base.BaseActivity
import com.brickmate.cube.ui.base.BaseFragment
import com.brickmate.cube.ui.login.view.BabyAvatarFragment
import com.brickmate.cube.ui.login.view.MainLoginFragment
import com.brickmate.cube.utils.popBackStack
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {

    private val arrowBack by lazy { clNavigation.findViewById<ImageView>(R.id.ivBack) }
    private val arrowNext by lazy { clNavigation.findViewById<ImageView>(R.id.ivNext) }

    override fun layoutId() = R.layout.activity_login

//    override fun fragment() = MainLoginFragment.newInstance()
    override fun fragment() = BabyAvatarFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //navigation
        onBackArrowPressed()
        onNextArrowPressed()
    }

    private fun onBackArrowPressed() {
        arrowBack.setOnClickListener {
//            popBackStackInclusive()
            popBackStack()
        }
    }

    private fun onNextArrowPressed() {
        arrowNext.setOnClickListener {
            val currentFragment =
                (supportFragmentManager.findFragmentById(R.id.clContainer) as BaseFragment)
            currentFragment.onNextArrowPressed()
        }
    }
}