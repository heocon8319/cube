package com.brickmate.cube.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


inline fun <reified T> T.TAG(): String = T::class.java.simpleName

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun AppCompatActivity.addFragment(containerId: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.inTransaction { add(containerId, fragment, tag) }
}

fun AppCompatActivity.replaceFragment(containerId: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.inTransaction { replace(containerId, fragment, tag) }
}

fun AppCompatActivity.getCurrentFragment(): Fragment? {
    val fragmentManager = supportFragmentManager
    var fragmentTag: String? = ""
    if (fragmentManager.backStackEntryCount > 0)
        fragmentTag =
            fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name
    return fragmentManager.findFragmentByTag(fragmentTag)
}

fun AppCompatActivity.popBackStack() {
    hideKeyboard()
    supportFragmentManager.popBackStack()
}

fun AppCompatActivity.popBackStackInclusive() {
    hideKeyboard()
    if (supportFragmentManager.backStackEntryCount > 0)
        supportFragmentManager.popBackStack(
            supportFragmentManager.getBackStackEntryAt(0).id,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}