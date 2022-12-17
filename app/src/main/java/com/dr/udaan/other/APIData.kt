package com.dr.udaan.other

import com.dr.udaan.retrofit.Pojo.CategoryData
import com.dr.udaan.retrofit.Retrofitinstance
import retrofit2.await

object APIData {

    suspend fun fetchCategories(): ArrayList<CategoryData> {

        var categoryData = ArrayList<CategoryData>()
        try {
            val response = Retrofitinstance.getRetrofit().categories().await()

            categoryData = response.categoryData

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return categoryData
    }

}