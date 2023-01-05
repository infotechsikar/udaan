package com.dr.udaan.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//
//@Entity(tableName = "saved")
//data class Saved(
//
//    @ColumnInfo(name = "id")
//    @PrimaryKey(autoGenerate = true)
//    val id: Int? = null,
//
//
//
//)

@Entity(tableName = "userData")
data class UserData (

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id"                ) var id              : Int?    = null,
    @SerializedName("name"              ) var name            : String? = null,
    @SerializedName("email"             ) var email           : String? = null,
    @SerializedName("email_verified_at" ) var emailVerifiedAt : String? = null,
    @SerializedName("status"            ) var status          : String? = null,
    @SerializedName("approval_status"   ) var approvalStatus  : String? = null,
    @SerializedName("app_role_id"       ) var appRoleId       : Int?    = null,
    @SerializedName("place_id"          ) var placeId         : String? = null,
    @SerializedName("latitude"          ) var latitude        : String? = null,
    @SerializedName("longitude"         ) var longitude       : String? = null,
    @SerializedName("mobile_no"         ) var mobileNo        : String? = null,
    @SerializedName("otp"               ) var otp             : String? = null,
    @SerializedName("otp_status"        ) var otpStatus       : Int?    = null,
    @SerializedName("balance"           ) var balance         : Int?    = null,
    @SerializedName("address"           ) var address         : String? = null,
    @SerializedName("about"             ) var about           : String? = null,
    @SerializedName("profile_image"     ) var profileImage    : String? = null,
    @SerializedName("charge"            ) var charge          : String? = null,
    @SerializedName("is_deleted"        ) var isDeleted       : String? = null

)
