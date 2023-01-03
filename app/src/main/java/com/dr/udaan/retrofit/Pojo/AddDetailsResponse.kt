package com.dr.udaan.retrofit.Pojo

import com.google.gson.annotations.SerializedName

class AddDetailsResponse (

    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("data"    ) var data    : Data?    = Data(),
    @SerializedName("message" ) var message : String?  = null

)

data class Data (

    @SerializedName("id"          ) var id         : Int?    = null,
    @SerializedName("name"        ) var name       : String? = null,
    @SerializedName("email"       ) var email      : String? = null,
    @SerializedName("status"      ) var status     : String? = null,
    @SerializedName("mobile_no"   ) var mobileNo   : String? = null,
    @SerializedName("address"     ) var address    : String? = null,
    @SerializedName("dob"         ) var dob        : String? = null,
    @SerializedName("category_id" ) var categoryId : Int?    = null

)