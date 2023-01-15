package com.dr.udaan.api.retrofit.Pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class BlogsResponse(

    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("blogdata") var blogdata: ArrayList<BlogData> = arrayListOf()

)

@Entity(tableName = "blogs")
data class BlogData(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("is_saved") var is_saved: Boolean? = null

)