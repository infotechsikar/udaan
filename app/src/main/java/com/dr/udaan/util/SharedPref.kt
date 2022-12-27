package com.dr.udaan.util

import android.content.Context
import android.content.SharedPreferences

class SharedPref {

    companion object {

        private var prefs: SharedPreferences? = null

        private fun getPrefs(context: Context): SharedPreferences {
            return context.getSharedPreferences(prefs.toString(), Context.MODE_PRIVATE)
        }

        @JvmStatic
        fun setString(context: Context, key: String?, value: String?) {
            val editor = getPrefs(context).edit()
            editor.putString(key, value)
            editor.apply()
        }

        @JvmStatic
        fun setLong(context: Context, key: String, value: Long) {
            val editor = getPrefs(context).edit()
            editor.putLong(key, value)
            editor.apply()
        }

        fun setInt(context: Context, key: String?, value: Int) {
            val editor = getPrefs(context).edit()
            editor.putInt(key, value)
            editor.apply()
        }

        fun setFloat(context: Context, key: String?, value: Float) {
            val editor = getPrefs(context).edit()
            editor.putFloat(key, value)
            editor.apply()
        }

        fun setBoolean(context: Context, key: String, value: Boolean) {
            val editor = getPrefs(context).edit()
            editor.putBoolean(key, value)
            editor.apply()
        }

        fun getBoolean(context: Context, key: String): Boolean {
            return getPrefs(context).getBoolean(key, false)
        }

        fun insertStringSet(context: Context, key: String?, value: Set<String?>?) {
            getPrefs(context).edit().putStringSet(key, value).apply()
        }

        @JvmStatic
        fun getString(context: Context, key: String?): String? {
            return getPrefs(context).getString(key, "")
        }

        fun getLong(context: Context, key: String) = run {
            getPrefs(context).getLong(key, 0L)
        }

        fun getInt(context: Context, key: String?): Int {
            return getPrefs(context).getInt(key, 0)
        }

        fun getFloat(context: Context, key: String?): Float {
            return getPrefs(context).getFloat(key, 0.0f)
        }

        fun setDouble(context: Context, key: String, value: Double) {
            setString(context, key, value.toString())
        }

        fun getDouble(context: Context, key: String) : Double {
            return try {
                getString(context, key).toString().toDouble()
            } catch (e: Exception) {.0}
        }

        @JvmStatic
        fun deleteKey(context: Context, key: String?) {
            val editor = getPrefs(context).edit()
            editor.remove(key)
            editor.apply()
        }

        @JvmStatic
        fun logOut(context: Context) {
            val editor = getPrefs(context).edit()
            editor.clear()
            editor.apply()
        }

    }

}