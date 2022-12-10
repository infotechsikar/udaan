package com.dr.udaan.retrofit.Pojo

import com.google.gson.annotations.SerializedName


data class ResendOtpResponse (

    @SerializedName("success"     ) var success   : Boolean? = null,
    @SerializedName("message"     ) var message   : String?  = null,
    @SerializedName("otp"         ) var otp       : Int?     = null,
    @SerializedName("app_role_id" ) var appRoleId : String?  = null

)