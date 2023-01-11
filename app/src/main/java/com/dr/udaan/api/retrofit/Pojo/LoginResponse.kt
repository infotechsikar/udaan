package com.dr.udaan.api.retrofit.Pojo


import com.dr.udaan.room.UserData
import com.google.gson.annotations.SerializedName

data class LoginResponse (

 @SerializedName("success"  ) var success  : Boolean?  = null,
 @SerializedName("message"  ) var message  : String?   = null,
 @SerializedName("token"    ) var token    : Token?   = null,
 @SerializedName("userData" ) var userData : UserData? = UserData()

)

data class Token (

 @SerializedName("name"           ) var name          : String?           = null,
 @SerializedName("abilities"      ) var abilities     : ArrayList<String> = arrayListOf(),
 @SerializedName("expires_at"     ) var expiresAt     : String?           = null,
 @SerializedName("tokenable_id"   ) var tokenableId   : Int?              = null,
 @SerializedName("tokenable_type" ) var tokenableType : String?           = null,
 @SerializedName("updated_at"     ) var updatedAt     : String?           = null,
 @SerializedName("created_at"     ) var createdAt     : String?           = null,
 @SerializedName("id"             ) var id            : Int?              = null

)

