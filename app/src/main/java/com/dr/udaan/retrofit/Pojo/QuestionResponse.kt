package com.dr.udaan.retrofit.Pojo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class QuestionResponse {
    @SerializedName("success" )     var success :        Boolean? = null
    @SerializedName("message" )     var message :        String?  = null
    @SerializedName("QuestionData" )   var     questioData     : ArrayList<QuestionData> = arrayListOf()

}


@Parcelize
data class QuestionData (

@SerializedName("id"         ) var id        : Int?    = null,
@SerializedName("question"   ) var question  : String? = null,
@SerializedName("solution"   ) var solution  : String? = null,
@SerializedName("option_a"   ) var optionA   : String? = null,
@SerializedName("option_b"   ) var optionB   : String? = null,
@SerializedName("option_c"   ) var optionC   : String? = null,
@SerializedName("option_d"   ) var optionD   : String? = null,
@SerializedName("answer"     ) var answer    : String? = null,
@SerializedName("status"     ) var status    : String? = null,
@SerializedName("deleted"    ) var deleted   : String? = null,
@SerializedName("created_at" ) var createdAt : String? = null,
@SerializedName("updated_at" ) var updatedAt : String? = null

) : Parcelable