package com.dr.udaan.api.retrofit.Pojo


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class BlogsResponse (

        @SerializedName("success"  ) var success  : Boolean?            = null,
        @SerializedName("message"  ) var message  : String?             = null,
        @SerializedName("blogdata" ) var blogdata : ArrayList<Blogdata> = arrayListOf()

        )

@Parcelize
data class Blogdata (

        @SerializedName("id"          ) var id          : Int?    = null,
        @SerializedName("title"       ) var title       : String? = null,
        @SerializedName("description" ) var description : String? = null,
        @SerializedName("created_at"  ) var createdAt   : String? = null,
        @SerializedName("updated_at"  ) var updatedAt   : String? = null

):Parcelable