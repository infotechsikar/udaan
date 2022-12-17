package com.dr.udaan.retrofit.Pojo


import com.google.gson.annotations.SerializedName

data class LoginResponse (

 @SerializedName("success"  ) var success  : Boolean?,
 @SerializedName("message"  ) var message  : String?,
 @SerializedName("token"    ) var token    : Token?,
 @SerializedName("userData" ) var userDataLog : UserDataLog?

)

data class Token (

 @SerializedName("name"           ) var name          : String?,
 @SerializedName("abilities"      ) var abilities     : ArrayList<String>?,
 @SerializedName("expires_at"     ) var expiresAt     : String?,
 @SerializedName("tokenable_id"   ) var tokenableId   : Int?,
 @SerializedName("tokenable_type" ) var tokenableType : String?,
 @SerializedName("updated_at"     ) var updatedAt     : String?,
 @SerializedName("created_at"     ) var createdAt     : String?,
 @SerializedName("id"             ) var id            : Int?

)

data class UserDataLog (

 @SerializedName("id"                ) var id              : Int?,
 @SerializedName("name"              ) var name            : String?,
 @SerializedName("email"             ) var email           : String?,
 @SerializedName("email_verified_at" ) var emailVerifiedAt : String?,
 @SerializedName("status"            ) var status          : String?,
 @SerializedName("user_type"         ) var userType        : String?,
 @SerializedName("mobile_no"         ) var mobileNo        : String?,
 @SerializedName("otp"               ) var otp             : String?,
 @SerializedName("otp_status"        ) var otpStatus       : Int?,
 @SerializedName("created_at"        ) var createdAt       : String?,
 @SerializedName("updated_at"        ) var updatedAt       : String?

)