package kr.nodeline.choice.commons.utils

import android.content.Context
import android.content.SharedPreferences

private const val FILENAME = "prefs"
private const val LONGITUDE = "longitude"
private const val LATITUDE = "latitude"
private const val JWT = "jwt"
private const val PUSH_TOKEN = "pushToken"
private const val JOIN_TYPE = "joinType"
private const val CURRENT_ADDR = "currentAddr"

class MySharedPreferences(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(FILENAME, 0)

    var longitude: String?
        get() = prefs.getString(LONGITUDE, "")
        set(value) = prefs.edit().putString(LONGITUDE, value).apply()

    var latitude: String?
        get() = prefs.getString(LATITUDE, "")
        set(value) = prefs.edit().putString(LATITUDE, value).apply()

    var jwt: String?
        get() = prefs.getString(JWT, "")
        set(value) = prefs.edit().putString(JWT, value).apply()

    var pushToken: String?
        get() = prefs.getString(PUSH_TOKEN, "")
        set(value) = prefs.edit().putString(PUSH_TOKEN, value).apply()

    var joinType: String?
        get() = prefs.getString(JOIN_TYPE, "")
        set(value) = prefs.edit().putString(JOIN_TYPE, value).apply()

    var currentAddr: String?
        get() = prefs.getString(CURRENT_ADDR, "위치가 정확하지 않음")
        set(value) = prefs.edit().putString(CURRENT_ADDR, value).apply()
}