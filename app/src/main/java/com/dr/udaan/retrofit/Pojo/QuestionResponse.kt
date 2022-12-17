package com.dr.udaan.retrofit.Pojo

import com.google.gson.annotations.SerializedName

class QuestionResponse {
    @SerializedName("success" )     var success :        Boolean? = null
    @SerializedName("message" )     var message :        String?  = null
    @SerializedName("QuestionData" )   var     QuestioData     : ArrayList<String> = arrayListOf()

}