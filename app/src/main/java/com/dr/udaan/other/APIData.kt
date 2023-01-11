package com.dr.udaan.other

import com.dr.udaan.api.retrofit.AllRequest.TestRequest
import com.dr.udaan.api.retrofit.Pojo.CategoryData
import com.dr.udaan.api.retrofit.Pojo.TestData
import com.dr.udaan.api.retrofit.Retrofitinstance
import retrofit2.await
import retrofit2.awaitResponse

object APIData {

    suspend fun fetchCategories(): ArrayList<CategoryData> {

        var categoryData = ArrayList<CategoryData>()

        try {
            val response = Retrofitinstance.getRetrofit().categories().awaitResponse()
            if (response.isSuccessful){

                categoryData = response.body()!!.categoryData
            }

        }

         catch (e: Exception) {
            e.printStackTrace()
        }

        return categoryData
    }

//    suspend fun fetchTest(): ArrayList<TestData>{
//
//        var testData = ArrayList<TestData>()
//        val request = TestRequest (
//            "1"
//            )
//
//        try {
//
//            val response = Retrofitinstance.getRetrofit().test(request).await()
//            testData = response.testData
//        }
//
//        catch (e: Exception){
//            e.printStackTrace()
//        }
//        return testData
//    }
}