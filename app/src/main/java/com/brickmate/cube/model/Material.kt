package com.brickmate.cube.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Material(
    val name: String = "",
    val imgUrl: Int = 0,
    val unit: String = "",
    var rootWeight: Int = 0,
    var weight: Int = 0,
    var count: Int = 0,
) : Parcelable {
}