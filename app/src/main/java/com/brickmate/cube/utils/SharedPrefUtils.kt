package com.brickmate.cube.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Double.*
import java.lang.reflect.Type

class SharedPrefUtils(context: Context) {
    private var sp: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    //region login

    fun setToken(token: String?) {
        sp.edit().putString(Companion.TOKEN, token).apply()
    }

    fun getToken(): String? {
        return sp.getString(Companion.TOKEN, "")
    }


    fun setLogIn(login: Boolean) {
        sp.edit().putBoolean(LOGIN, login).apply()
    }

    fun getLoggIn(): Boolean {
        return sp.getBoolean(LOGIN, false)
    }

    //endregion

    //region username
    fun setNickName(userName: String?) {
        sp.edit().putString(NICKNAME, userName).apply()
    }

    fun getNickName(): String? {
        return sp.getString(NICKNAME, "")
    }

    fun setBabyName(babyName: String?) {
        sp.edit().putString(BABY_NAME, babyName).apply()
    }

    fun getBabyName(): String? {
        return sp.getString(BABY_NAME, "")
    }

    fun setBabyGender(gender: String?) {
        sp.edit().putString(BABY_GENDER, gender).apply()
    }

    fun getBabyGender(): String? {
        return sp.getString(BABY_GENDER, "")
    }

    fun setBirthday(birthday: String?) {
        sp.edit().putString(BIRTHDAY, birthday).apply()
    }

    fun getBirthday(): String? {
        return sp.getString(BIRTHDAY, "")
    }

    fun setHeight(value: Double) {
        sp.edit().putLong(HEIGHT, doubleToRawLongBits(value)).apply()
    }

    fun getHeight(): Double {
        return longBitsToDouble(sp.getLong(HEIGHT, doubleToLongBits(0.0)))
    }

    fun setWeight(value: Double) {
        sp.edit().putLong(WEIGHT, doubleToRawLongBits(value)).apply()
    }

    fun getWeight(): Double {
        return longBitsToDouble(sp.getLong(WEIGHT, doubleToLongBits(0.0)))
    }

    fun setAvatar(avatar: String?) {
        sp.edit().putString(AVATAR, avatar).apply()
    }

    fun getAvatar(): String? {
        return sp.getString(AVATAR, "")
    }

    fun setGoodIngredients(list: ArrayList<String>) {
        val json: String = Gson().toJson(list)
        sp.edit().putString(GOOD_INGREDIENT, json).apply()
    }

    fun setGoodIngredients(): ArrayList<String?>? {
        val json: String? = sp.getString(GOOD_INGREDIENT, null)
        val type: Type = object : TypeToken<java.util.ArrayList<String?>?>() {}.type
        return Gson().fromJson(json, type)
    }

    fun setAdverseIngredients(list: ArrayList<String>) {
        val json: String = Gson().toJson(list)
        sp.edit().putString(ADVERSE_INGREDIENT, json).apply()
    }

    fun setAdverseIngredients(): ArrayList<String?>? {
        val json: String? = sp.getString(ADVERSE_INGREDIENT, null)
        val type: Type = object : TypeToken<java.util.ArrayList<String?>?>() {}.type
        return Gson().fromJson(json, type)
    }

    fun setConstipation(cons: Boolean) {
        sp.edit().putBoolean(CONSTIPATION, cons).apply()
    }

    fun getConstipation(): Boolean {
        return sp.getBoolean(CONSTIPATION, false)
    }

    fun setCold(cold: Boolean) {
        sp.edit().putBoolean(COLD, cold).apply()
    }

    fun getCold(): Boolean {
        return sp.getBoolean(COLD, false)
    }

    fun setDiarrhea(dia: Boolean) {
        sp.edit().putBoolean(DIARRHEA, dia).apply()
    }

    fun getDiarrhea(): Boolean {
        return sp.getBoolean(DIARRHEA, false)
    }

    //endregion

    //region baby

    //endregion

    companion object {
        const val TOKEN = "TOKEN"
        const val LOGIN = "LOGIN"
        const val NICKNAME = "NICKNAME"
        const val BABY_NAME = "BABY_NAME"
        const val BABY_GENDER = "BABY_GENDER"
        const val BIRTHDAY = "BIRTHDAY"
        const val HEIGHT = "HEIGHT"
        const val WEIGHT = "WEIGHT"
        const val AVATAR = "AVATAR"
        const val GOOD_INGREDIENT = "GOOD_INGREDIENT"
        const val ADVERSE_INGREDIENT = "ADVERSE_INGREDIENT"
        const val CONSTIPATION = "CONSTIPATION"
        const val COLD = "COLD"
        const val DIARRHEA = "DIARRHEA"
    }
}