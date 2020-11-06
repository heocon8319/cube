package com.brickmate.cube

import android.app.Application
import com.brickmate.cube.utils.SharedPrefUtils

val sharedPrefs: SharedPrefUtils by lazy { CubeApplication.prefs!! }
class CubeApplication : Application() {
    companion object {
        var prefs: SharedPrefUtils? = null
    }

    override fun onCreate() {
        prefs = SharedPrefUtils(applicationContext)
        super.onCreate()
    }
}