package persistence.sharedPreferences

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences (context: Context) {

    private val PREFS_NAME = "com.tecnoMoviles.techKings.sharedPreferences"

    val preferences : SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val KEY_USER_NAME = "USER_NAME"
    private val KEY_REFRESH_ACTIVITY = "REFRESH_ACTIVITY"
    private val KEY_USER_AGE = "USER_AGE"
    private val KEY_ACTIVITY_NAME = "KEY_ACTIVITY_NAME"

    fun setUserName(userName: String) {
        preferences.edit().putString(KEY_USER_NAME, userName).apply()
    }

    fun setRefreshActivity(refresh:Boolean) {
        preferences.edit().putString(KEY_REFRESH_ACTIVITY, refresh.toString()).apply()
    }

    fun setActivityName(activityName: String) {
        preferences.edit().putString(KEY_ACTIVITY_NAME, activityName).apply()
    }

    fun getActivityName(): String {
        return preferences.getString(KEY_ACTIVITY_NAME, "") ?: ""
    }

    fun getRefreshActivity(): String {
        return preferences.getString(KEY_REFRESH_ACTIVITY, "") ?: ""
    }


    fun getUserName(): String {
        return preferences.getString(KEY_USER_NAME, "") ?: ""
    }

    fun setUserAge(userAge: Int) {
        preferences.edit().putInt(KEY_USER_AGE, userAge).apply()
    }

    fun getUserAge(): Int {
        return preferences.getInt(KEY_USER_AGE, 0)
    }

}