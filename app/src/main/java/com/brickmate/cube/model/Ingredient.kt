package com.brickmate.cube.model

import android.os.Parcelable
import com.brickmate.cube.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingredient(
    val name: String = "",
    var isSelected: Boolean = false,
) : Parcelable {

    fun getLayout(): Int {
        return  if (isSelected) R.drawable.bg_item_grid_view_green else R.drawable.bg_item_grid_view_gray
    }

    fun getTextColor(): Int {
        return  if (isSelected) R.color.white else R.color.rolling_stone
    }
}