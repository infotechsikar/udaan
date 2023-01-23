package com.dr.udaan.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.dr.udaan.MyApp
import com.dr.udaan.util.Const.ABOUT_US
import com.dr.udaan.util.Const.AUTH_STATUS
import com.dr.udaan.util.Const.AUTH_STATUS_DONE
import com.dr.udaan.util.Const.EMAIL
import com.dr.udaan.util.Const.NOTIFICATION_ENABLED
import com.dr.udaan.util.Const.PHONE
import com.dr.udaan.util.Const.PRIVACY_POLICY
import com.dr.udaan.util.Const.PROFILE_URL
import com.dr.udaan.util.Const.REFUND_POLICY
import com.dr.udaan.util.Const.TERMS_AND_CONDITIONS
import com.dr.udaan.util.Const.USER_NAME
import java.io.ByteArrayOutputStream


object AppFunctions {

    fun isUserVerified() =
        MyApp.myDatabase?.userData()?.getUser() != null && MyApp.myDatabase!!.userData()
            .getUser()?.id != null

    fun isNotificationEnabled(mContext: Context) =
        SharedPref.getBoolean(mContext, NOTIFICATION_ENABLED)

    fun setNotificationEnabled(mContext: Context, isEnable: Boolean) =
        SharedPref.setBoolean(mContext, NOTIFICATION_ENABLED, isEnable)

    fun getUserName(mContext: Context): String {
        return SharedPref.getString(mContext, USER_NAME).toString()
    }
    fun getUserId() = MyApp.myDatabase?.userData()?.getUser()?.id ?: -1

    fun getEmail(mContext: Context): String {
        return SharedPref.getString(mContext, EMAIL).toString()
    }

    fun getPhone(mContext: Context): String {
        return SharedPref.getString(mContext, PHONE).toString()
    }

    fun setUserName(mContext: Context, name: String) {
        SharedPref.setString(mContext, USER_NAME, name)
    }

    fun setEmail(mContext: Context, email: String) {
        SharedPref.setString(mContext, EMAIL, email)
    }

    fun setPhone(mContext: Context, phone: String) {
        SharedPref.setString(mContext, PHONE, phone)
    }

    fun setProfileUrl(mContext: Context, profileUrl: String) {
        SharedPref.setString(mContext, PROFILE_URL, profileUrl)
    }

    fun getProfileUrl(mContext: Context): String {
        return SharedPref.getString(mContext, PROFILE_URL).toString()
    }


    fun logOut(mContext: Context) {
        SharedPref.logOut(mContext)
    }


    fun getAboutUs(mContext: Context): String {
        return SharedPref.getString(mContext, ABOUT_US).toString()
    }

    fun getPrivacyPolicy(mContext: Context): String {
        return SharedPref.getString(mContext, PRIVACY_POLICY).toString()
    }

    fun getTermsAndConditions(mContext: Context): String {
        return SharedPref.getString(mContext, TERMS_AND_CONDITIONS).toString()
    }

    fun getRefundPolicy(mContext: Context): String {
        return SharedPref.getString(mContext, REFUND_POLICY).toString()
    }

    fun setAboutUs(mContext: Context, data: String) {
        SharedPref.setString(mContext, ABOUT_US, data).toString()
    }

    fun setPrivacyPolicy(mContext: Context, data: String) {
        SharedPref.setString(mContext, PRIVACY_POLICY, data).toString()
    }

    fun setTermsAndConditions(mContext: Context, data: String) {
        SharedPref.setString(mContext, TERMS_AND_CONDITIONS, data).toString()
    }

    fun setRefundPolicy(mContext: Context, data: String) {
        SharedPref.setString(mContext, REFUND_POLICY, data).toString()
    }


    fun checkConnection(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection

        // Returns a Network object corresponding to
        // the currently active default data network.
        val network = connectivityManager.activeNetwork ?: return false

        // Representation of the capabilities of an active network.
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            // Indicates this network uses a Wi-Fi transport,
            // or WiFi has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            // Indicates this network uses a Cellular transport. or
            // Cellular has network connectivity
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            // else return false
            else -> false
        }

    }

    fun openKeyboard(activity: AppCompatActivity) {
        val imm: InputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun closeKeyboard(activity: AppCompatActivity, rootView: View) {
        val imm: InputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInputFromWindow(rootView.windowToken, 0, 0)
    }

    fun uriToByteArray(uri: Uri): ByteArray {
        val inputStream = MyApp.appContext?.contentResolver?.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

}