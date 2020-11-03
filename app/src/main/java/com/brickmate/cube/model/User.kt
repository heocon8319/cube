package com.brickmate.cube.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val email : String = "",
    val password : String = "",
    val userName : String = "",
    val babyName : String = "",
    val babyGender : String = "",

    val age : Int = 0
) : Parcelable