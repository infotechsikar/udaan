package com.dr.udaan.api

import com.dr.udaan.MyApp.Companion.myDatabase
import com.dr.udaan.api.retrofit.Retrofitinstance.getRetrofit
import retrofit2.await

object TestsAPI {

    suspend fun fetchTests(categoryId: Int, pageNo: Int)  {

        if (pageNo == 1) {
            myDatabase?.tests()?.deleteAllExceptSaved()
        }

        val response = try {
            getRetrofit().test(categoryId, pageNo).await()
        } catch (e: Exception) {
            null
        }

        response?.testData?.map {
            myDatabase?.tests()?.insert(it)
        }

    }

    suspend fun fetchQuestions(testId: Int) {



    }

}