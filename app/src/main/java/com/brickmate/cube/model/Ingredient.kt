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
        var default = R.drawable.bg_item_grid_view_gray
        if (isSelected) {
            default = R.drawable.bg_item_grid_view_green
        }
        return default
    }
}
