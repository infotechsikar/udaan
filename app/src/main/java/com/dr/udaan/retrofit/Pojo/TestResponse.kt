package com.dr.udaan.retrofit.Pojo

import com.google.gson.annotations.SerializedName

data class TestResponse (

    @SerializedName("success"  ) var success  : Boolean?            = null,
    @SerializedName("message"  ) var message  : String?             = null,
    @SerializedName("testData" ) var testData : ArrayList<TestData> = arrayListOf()

)

data class TestData (
    @SerializedName("id"          ) var id          : Int?              = null,
    @SerializedName("category_id" ) var categoryId  : Int?              = null,
    @SerializedName("title"       ) var title       : String?           = null,
    @SerializedName("test_image"  ) var testImage   : String?           = null,
    @SerializedName("item_type"   ) var itemType    : String?           = null,
    @SerializedName("price"       ) var price       : Int?              = null,
    @SerializedName("description" ) var description : String?           = null,
    @SerializedName("deleted"     ) var deleted     : String?           = null,
    @SerializedName("created_at"  ) var createdAt   : String?           = null,
    @SerializedName("updated_at"  ) var updatedAt   : String?           = null,
    @SerializedName("question"    ) var question    : ArrayList<QuestionData> = arrayListOf()

)