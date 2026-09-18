package com.example.bloom.data

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("bloom_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"
        private const val KEY_USER_NAME = "user_name"
    }

    var isOnboardingCompleted: Boolean
        get() = sharedPreferences.getBoolean(KEY_ONBOARDING_COMPLETED, false)
        set(value) {
            sharedPreferences.edit().putBoolean(KEY_ONBOARDING_COMPLETED, value).apply()
        }

    var userName: String
        get() = sharedPreferences.getString(KEY_USER_NAME, "") ?: ""
        set(value) {
            sharedPreferences.edit().putString(KEY_USER_NAME, value).apply()
        }
}
