package com.brickmate.cube.model

import android.os.Parcelable
import android.view.View
import com.brickmate.cube.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodayMeal(
    var isMeal: Boolean = true,
    var capacity: Int = 0,
    var time: String = "",
    var name: String = "",
    var ingredients: ArrayList<String>? = null
) : Parcelable {

    fun getBackground(): Int {
        return if (isMeal) R.drawable.bg_today_meal else R.drawable.bg_today_milk
    }

    fun getIconMeal(): Int {
        return if (capacity > 0) {
            if (isMeal) R.drawable.ic_today_meal else R.drawable.ic_today_milk
        } else {
            if (isMeal) R.drawable.ic_today_empty_meal else R.drawable.ic_today_empty_meal
        }
    }

    fun getIngredientString(): String {
        var result = StringBuilder()
        if(!ingredients.isNullOrEmpty()){
            ingredients!!.forEach {
                result.append("$it ")
            }
        }
        return result.toString()
    }

    fun getVisibleClock(): Int {
        return if (time.isNullOrEmpty()) View.GONE else View.VISIBLE
    }
}