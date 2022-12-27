package com.dr.udaan.retrofit.Pojo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

class QuestionResponse {

    @SerializedName("success")         var success        : Boolean? = null
    @SerializedName("message")         var message        : String?  = null
    @SerializedName("Question Data")   var QuestionData   : ArrayList<QuestionData> = arrayListOf()

}

@Parcelize
class QuestionData : Parcelable {

    @IgnoredOnParcel
    @SerializedName("id") var id: Int? = null
    @IgnoredOnParcel
    @SerializedName("question") var question: String? = null
    @IgnoredOnParcel
    @SerializedName("solution") var solution: String? = null
    @IgnoredOnParcel
    @SerializedName("option_a") var optionA: String? = null
    @IgnoredOnParcel
    @SerializedName("option_b") var optionB: String? = null
    @IgnoredOnParcel
    @SerializedName("option_c") var optionC: String? = null
    @IgnoredOnParcel
    @SerializedName("option_d") var optionD: String? = null
    @IgnoredOnParcel
    @SerializedName("answer") var answer: String? = null
    @IgnoredOnParcel
    @SerializedName("status") var status: String? = null
    @IgnoredOnParcel
    @SerializedName("deleted") var deleted: String? = null
    @IgnoredOnParcel
    @SerializedName("created_at") var createdAt: String? = null
    @IgnoredOnParcel
    @SerializedName("updated_at") var updatedAt: String? = null

}