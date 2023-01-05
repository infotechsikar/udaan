package com.dr.udaan.api.retrofit.Pojo


import com.dr.udaan.room.UserData
import com.google.gson.annotations.SerializedName

data class LoginResponse (

 @SerializedName("success"  ) var success  : Boolean?  = null,
 @SerializedName("message"  ) var message  : String?   = null,
 @SerializedName("token"    ) var token    : String?   = null,
 @SerializedName("userData" ) var userData : UserData? = UserData()

)

