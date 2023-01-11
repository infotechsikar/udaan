package com.dr.udaan.api.retrofit

import com.dr.udaan.RegisterResponse
import com.dr.udaan.api.retrofit.AllRequest.RegisterRequest
import com.dr.udaan.api.retrofit.AllRequest.ResendOtpRequest
import com.dr.udaan.api.retrofit.AllRequest.VerifyOtpRequest
import com.dr.udaan.api.retrofit.Pojo.*
import com.dr.udaan.api.retrofit.AllRequest.AddDetailRequest
import com.dr.udaan.retrofit.Pojo.AddDetailsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitAPI {

    @POST("register")
    fun register(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("mobile_no") mobileNo: String,
        @Field("password") password : String
    ): Call<LoginResponse>

    @POST("verify-otp")
    fun verifyOtp(@Body verifyOtpRequest: VerifyOtpRequest): Call<VerifyOtpResponse>

    @FormUrlEncoded
    @POST("resend-otp")
    fun resendOtp(@Body resendOtpRequest: ResendOtpRequest):Call<ResendOtpResponse>

    @POST("category")
    fun categories(): Call<CategoriesResponse>

    @POST("slider")
    fun sliders():Call<SliderResponse>

    @FormUrlEncoded
    @POST("test")
    fun test(
        @Field("category_id") categoryId : Int,
        @Field("page_no") pageNo : Int
    ): Call<TestResponse>

    @FormUrlEncoded
    @POST("questions")
    fun question(
        @Field("test_id") testId:Int
    ) : Call<QuestionResponse?>?

    @POST("questions")
    fun questions(@Field("test_id") testId: Int): Call<QuestionResponse>

    @FormUrlEncoded
    @POST("add-details")
    fun addDetails(
        @Field ("user_id") userId: Int,
        @Field ("name") name: String,
        @Field ("email") email: String,
        @Field ("dob") dob: String,
        @Field("category_id") categoryId: Int,
        @Field("address") address : String
    ) : Call<AddDetailsResponse>

    @POST("add-details")
    fun addDetail(@Body addDetailRequest: AddDetailRequest):Call<AddDetailsResponse>

    @POST("blogs")
    fun blogs():Call<BlogsResponse>

}
