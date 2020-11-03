package com.brickmate.cube.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.transact(action: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().action().commitAllowingStateLoss()
}

fun Fragment.addFragment(fragment: Fragment, addToBackStack: Boolean = false, tag: String?) {
    fragmentManager?.transact {
        if (addToBackStack) {
            addToBackStack(tag)
        }
        add(fragment, tag)
    }
}

fun Fragment.replaceFragment(
    containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false,
    tag: String?
) {
    fragmentManager?.transact {
        if (addToBackStack) {
            addToBackStack(tag)
        }
        replace(containerId, fragment, tag)
    }
}

fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}