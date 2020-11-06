package com.brickmate.cube.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefUtils(context: Context) {
    private var sp: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    //region login

    fun setToken(token: String?) {
        sp.edit().putString(Companion.TOKEN, token).commit()
    }

    fun getToken(): String? {
        return sp.getString(Companion.TOKEN, "")
    }


    fun setLogIn(login: Boolean) {
        sp.edit().putBoolean(LOGIN, login).commit()
    }

    fun isLoggedIn(): Boolean {
        return sp.getBoolean(LOGIN, false)
    }

    //endregion

    //region username

    fun setUserName(userName: String?) {
        sp.edit().putString(USERNAME, userName).commit()
    }

    fun getUserName(): String? {
        return sp.getString(USERNAME, "")
    }

    //endregion

    //region baby


    //endregion

    companion object {
        const val TOKEN = "token"
        const val LOGIN = "login"
        const val USERNAME = "userName"
    }
}