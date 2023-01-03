package com.dr.udaan.retrofit.Pojo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

class QuestionResponse {


    @SerializedName("success" )     var success :        Boolean? = null
    @SerializedName("message" )     var message :        String?  = null
    @SerializedName("Question Data" )   var     QuestionData     : ArrayList<QuestionData> = arrayListOf()


}

@Parcelize
class QuestionData : Parcelable {

    @SerializedName("id"         ) var id        : Int?    = null
    @SerializedName("question"   ) var question  : String? = null
    @SerializedName("solution"   ) var solution  : String? = null
    @SerializedName("option_a"   ) var optionA   : String? = null
    @SerializedName("option_b"   ) var optionB   : String? = null
    @SerializedName("option_c"   ) var optionC   : String? = null
    @SerializedName("option_d"   ) var optionD   : String? = null
    @SerializedName("answer"     ) var answer    : String? = null
    @SerializedName("status"     ) var status    : String? = null
    @SerializedName("created_at" ) var createdAt : String? = null
    @SerializedName("updated_at" ) var updatedAt : String? = null


}