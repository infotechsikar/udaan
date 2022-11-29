package com.dr.udaan.retrofit

import com.dr.udaan.retrofit.Pojo.DummyResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitAPI {
    @FormUrlEncoded
    @POST("login")
    fun createPost(@Field("mobile_no") phone: String, @Field("password") password: String): Call<DummyResponse?>?

}
