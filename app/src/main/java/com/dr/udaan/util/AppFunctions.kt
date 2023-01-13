package com.dr.udaan.util

import android.content.Context
import com.dr.udaan.util.Const.AUTH_STATUS
import com.dr.udaan.util.Const.AUTH_STATUS_DONE
import com.dr.udaan.util.Const.EMAIL
import com.dr.udaan.util.Const.PHONE
import com.dr.udaan.util.Const.PROFILE_URL
import com.dr.udaan.util.Const.USER_NAME

object AppFunctions {

    fun mUid(mContext : Context) : String {
        return SharedPref.getString(mContext, PHONE).toString()
    }

    fun isUserVerified(mContext: Context) = getAuthStatus(mContext) == AUTH_STATUS_DONE

    private fun getAuthStatus(mContext: Context) : String {
        return SharedPref.getString(mContext,AUTH_STATUS).toString()
    }

    fun setUserVerified(mContext: Context) = SharedPref.setString(mContext, AUTH_STATUS, AUTH_STATUS_DONE)

    fun getUserName(mContext: Context) : String {
        return SharedPref.getString(mContext, USER_NAME).toString()
    }

    fun getEmail(mContext: Context) : String {
        return SharedPref.getString(mContext, EMAIL).toString()
    }

    fun getPhone(mContext: Context) : String {
        return SharedPref.getString(mContext, PHONE).toString()
    }

    fun setUserName(mContext: Context, name : String)  {
        SharedPref.setString(mContext, USER_NAME, name)
    }

    fun setEmail(mContext: Context, email : String)   {
        SharedPref.setString(mContext, EMAIL, email)
    }

    fun setPhone(mContext: Context, phone : String)   {
        SharedPref.setString(mContext, PHONE, phone)
    }

    fun setProfileUrl(mContext: Context, profileUrl : String)  {
        SharedPref.setString(mContext, PROFILE_URL, profileUrl)
    }

    fun getProfileUrl(mContext: Context) : String {
        return SharedPref.getString(mContext, PROFILE_URL).toString()
    }


    fun logOut(mContext: Context) {
        SharedPref.logOut(mContext)
    }

    // Privacy

//    fun getAboutUs(mContext: Context) : String {
//        return SharedPref.getString(mContext, ABOUT_US).toString()
//    }
//    fun getContactUs(mContext: Context) : String {
//        return SharedPref.getString(mContext, CONTACT_US).toString()
//    }
//    fun getPrivacyPolicy(mContext: Context) : String {
//        return SharedPref.getString(mContext, PRIVACY_POLICY).toString()
//    }
//    fun getTermsAndConditions(mContext: Context) : String {
//        return SharedPref.getString(mContext, TERMS_AND_CONDITIONS).toString()
//    }
//    fun getRefundPolicy(mContext: Context) : String {
//        return SharedPref.getString(mContext, REFUND_POLICY).toString()
//    }
//    fun getShippingPolicy(mContext: Context) : String {
//        return SharedPref.getString(mContext, SHIPPING_POLICY).toString()
//    }
//
//    fun setAboutUs(mContext: Context, data : String)   {
//        SharedPref.setString(mContext, ABOUT_US, data).toString()
//    }
//    fun setContactUs(mContext: Context, data : String)  {
//        SharedPref.setString(mContext, CONTACT_US, data).toString()
//    }
//    fun setPrivacyPolicy(mContext: Context, data : String)  {
//        SharedPref.setString(mContext, PRIVACY_POLICY, data).toString()
//    }
//
//    fun setTermsAndConditions(mContext: Context, data : String)  {
//        SharedPref.setString(mContext, TERMS_AND_CONDITIONS, data).toString()
//    }
//
//    fun setRefundPolicy(mContext: Context, data : String)  {
//        SharedPref.setString(mContext, REFUND_POLICY, data).toString()
//    }
//
//    fun setShippingPolicy(mContext: Context, data : String)  {
//        SharedPref.setString(mContext, SHIPPING_POLICY, data).toString()
//    }

}