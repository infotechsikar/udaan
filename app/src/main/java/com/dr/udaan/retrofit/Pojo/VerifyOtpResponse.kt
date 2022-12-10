package com.dr.udaan.retrofit.Pojo

import com.google.gson.annotations.SerializedName

 data class VerifyOtpResponse(
     @SerializedName("success"  ) var success: Boolean?  = null,
     @SerializedName("message"  ) var message: String?   = null,
     @SerializedName("userData" ) var userData: com.dr.udaan.retrofit.Pojo.UserData = UserData()

 )

 data class UserData (

    @SerializedName("id"                ) var id              : Int?    = null,
    @SerializedName("name"              ) var name            : String? = null,
    @SerializedName("email"             ) var email           : String? = null,
    @SerializedName("email_verified_at" ) var emailVerifiedAt : String? = null,
    @SerializedName("status"            ) var status          : String? = null,
    @SerializedName("user_type"         ) var userType        : String? = null,
    @SerializedName("mobile_no"         ) var mobileNo        : String? = null,
    @SerializedName("otp"               ) var otp             : String? = null,
    @SerializedName("otp_status"        ) var otpStatus       : Int?    = null,
    @SerializedName("created_at"        ) var createdAt       : String? = null,
    @SerializedName("updated_at"        ) var updatedAt       : String? = null

 )

