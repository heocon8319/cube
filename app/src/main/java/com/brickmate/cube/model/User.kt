package com.brickmate.cube.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val no: Int = 0,
    val email: String = "",
    val nickname: String = "",
    val birthday: String? = null,
    val phoneNumber: String = ""
) : Parcelable