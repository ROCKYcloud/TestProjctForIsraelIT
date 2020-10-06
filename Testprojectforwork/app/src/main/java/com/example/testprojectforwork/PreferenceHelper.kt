package com.example.testprojectforwork

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {
    val USER_EMAIL = "USER_ID"
    val USER_SING_UP = "USER_SING_UP"
    val EMAIL = "EMAIL"
    val PASSWORD = "USER_PASSWORD"

    fun customPreference(context: Context, name: String?): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.username
        get() = getString(USER_EMAIL, "")
        set(value) {
            editMe {
                it.putString(USER_EMAIL, value)
            }
        }

    var SharedPreferences.email
        get() = getString(EMAIL, "")
        set(value) {
            editMe {
                it.putString(EMAIL, value)
            }
        }

    var SharedPreferences.password
        get() = getString(PASSWORD, "")
        set(value) {
            editMe {
                it.putString(PASSWORD, value)
            }
        }

    //TODO bool when user in app or out
    var SharedPreferences.isLogin
        get() = getBoolean(USER_SING_UP, false)
        set(value) {
            editMe {
                it.putBoolean(USER_SING_UP, value)
            }
        }
}
