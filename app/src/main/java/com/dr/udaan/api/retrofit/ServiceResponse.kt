package com.dr.udaan.api.retrofit

import com.dr.udaan.api.retrofit.Pojo.TestData
import com.google.gson.annotations.SerializedName

class ServiceResponse<data: Any> {

    @SerializedName("success")         var success        : Boolean? = null
    @SerializedName("message")         var message        : String?  = null
    @SerializedName("Question Data")   var question_data   : data? = null

}

class SearchServiceResponse {

    @SerializedName("success")         var success        : Boolean? = null
    @SerializedName("message")         var message        : String?  = null
    @SerializedName("search_result")   var searchResult   : List<TestData>? = null

}

