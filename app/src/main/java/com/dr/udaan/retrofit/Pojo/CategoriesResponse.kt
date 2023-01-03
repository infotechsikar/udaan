package com.dr.udaan.retrofit.Pojo

import com.google.gson.annotations.SerializedName

data class CategoriesResponse (

    @SerializedName("success"      ) var success      : Boolean?                = null,
    @SerializedName("message"      ) var message      : String?                 = null,
    @SerializedName("categoryData" ) var categoryData : ArrayList<CategoryData> = arrayListOf()

)

data class CategoryData (

    @SerializedName("id"             ) var id            : Int?    = null,
    @SerializedName("category_name"  ) var categoryName  : String? = null,
    @SerializedName("category_image" ) var categoryImage : String? = null,
    @SerializedName("deleted"        ) var deleted       : String? = null

)