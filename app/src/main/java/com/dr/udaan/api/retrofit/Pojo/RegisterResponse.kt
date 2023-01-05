package com.dr.udaan

import com.google.gson.annotations.SerializedName


data class RegisterResponse (

    @SerializedName("success"    ) var success   : Boolean? = null,
    @SerializedName("message"    ) var message   : String?  = null,
    @SerializedName("user_id"    ) var userId    : Int?     = null,
    @SerializedName("otp"        ) var otp       : Int?     = null,
    @SerializedName("otp_status" ) var otpStatus : Boolean? = null

)