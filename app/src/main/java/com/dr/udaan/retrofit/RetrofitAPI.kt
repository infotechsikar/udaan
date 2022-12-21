package com.dr.udaan.retrofit

import com.dr.udaan.RegisterResponse
import com.dr.udaan.retrofit.AllRequest.LoginRequest
import com.dr.udaan.retrofit.AllRequest.RegisterRequest
import com.dr.udaan.retrofit.AllRequest.ResendOtpRequest
import com.dr.udaan.retrofit.AllRequest.VerifyOtpRequest
import com.dr.udaan.retrofit.Pojo.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitAPI {

    @POST("register")
    fun register(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("verify-otp")
    fun verifyOtp(@Body verifyOtpRequest: VerifyOtpRequest): Call<VerifyOtpResponse>

    @FormUrlEncoded
    @POST("resend-otp")
    fun resendOtp(@Body resendOtpRequest: ResendOtpRequest):Call<ResendOtpResponse>

    @POST("category")
    fun categories(): Call<CategoriesResponse>

    @FormUrlEncoded
    @POST("test")
    fun getTestData(
        @Field("category_id") categoryId : Int
    ) : Call<TestResponse?>?

}
