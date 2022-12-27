package com.dr.udaan.retrofit.Pojo

import com.google.gson.annotations.SerializedName

class SliderResponse {

    @SerializedName("success"      ) var success      : Boolean?                = null
    @SerializedName("message"      ) var message      : String?                 = null
    @SerializedName("categoryData" ) var categoryData : ArrayList<SliderData> = arrayListOf()

}

data class SliderData (

    @SerializedName("id"           ) var id          : Int?    = null,
    @SerializedName("slider_image" ) var sliderImage : String? = null,
    @SerializedName("deleted"      ) var deleted     : String? = null

)
