package com.dr.udaan.api.retrofit.Pojo

import com.google.gson.annotations.SerializedName

 data class SubmitAnswer (


    @SerializedName("user_id"     ) var userId     : Int?                  = null,
    @SerializedName("test_id"     ) var testId     : Int?                  = null,
    @SerializedName("answer_data" ) var answerData : ArrayList<AnswerData> = arrayListOf()

)


data class AnswerData (

    @SerializedName("question_id" ) var questionId : Int? = null,
    @SerializedName("answer"      ) var answer     : Int? = null

)