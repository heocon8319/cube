package com.brickmate.cube.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.brickmate.cube.AppConstants
import com.brickmate.cube.R
import com.brickmate.cube.ui.login.LoginActivity
import com.brickmate.cube.utils.replaceFragment
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_navigation_background.clNavigation

abstract class BaseFragment : Fragment(), AppConstants {

    internal lateinit var activity: BaseActivity

    abstract fun layoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hideProgress()
        hideArrow()
        hideBackground()
        return inflater.inflate(layoutId(), container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as BaseActivity
    }

    open fun onBackPressed() {}

    open fun onNextArrowPressed() {}

    internal fun navigateToFragment(fragDes: BaseFragment, tag: String) {
        fragDes?.let {
            replaceFragment(
                R.id.clContainer,
                it,
                true,
                tag
            )
        }
    }

    internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

    //ProcessBar
    internal fun showProgress() = progressStatus(View.VISIBLE)

    internal fun hideProgress() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) =
        with(activity) { if (this is BaseActivity) this.clProgressBar.visibility = viewStatus }

    /* Login Activity */

    //Arrow Navigation
    internal fun showArrow() = arrowStatus(View.VISIBLE)

    internal fun hideArrow() = arrowStatus(View.GONE)

    private fun arrowStatus(viewStatus: Int) =
        with(activity) { if (this is LoginActivity) this.clNavigation.visibility = viewStatus }

    //Image Background
    internal fun showBackground() = backgroundStatus(View.VISIBLE)

    internal fun hideBackground() = backgroundStatus(View.GONE)

    private fun backgroundStatus(viewStatus: Int) =
        with(activity) { if (this is LoginActivity) this.ivBackground.visibility = viewStatus }

    /* Main Activity */

}