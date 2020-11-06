package com.brickmate.cube.model

import android.os.Parcelable
import com.brickmate.cube.BabyGender
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Baby(
    val no: Int = 0,
    val name: String = "",
    val sex: BabyGender = BabyGender.FEMALE,
    val birthday: String = "",
    val profileImageUrl: String = "",
    val height: Double = 0.0,
    val weight: Double = 0.0,
    val diarrhea: Boolean = false,
    val cold: Boolean = false,
    val constipation: Boolean = false,
    val daysOfDiarrhea: Int = 0,
    val daysOfCold: Int = 0,
    val daysOfConstipation: Int = 0
) : Parcelable