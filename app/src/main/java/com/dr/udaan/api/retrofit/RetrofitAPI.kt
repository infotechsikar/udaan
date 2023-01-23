package com.dr.udaan.api.retrofit

import com.dr.udaan.api.retrofit.Pojo.RegisterResponse
import com.dr.udaan.api.retrofit.AllRequest.RegisterRequest
import com.dr.udaan.api.retrofit.AllRequest.ResendOtpRequest
import com.dr.udaan.api.retrofit.AllRequest.VerifyOtpRequest
import com.dr.udaan.api.retrofit.Pojo.*
import okhttp3.ResponseBody
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
    ) : Call<ServiceResponse<List<QuestionData>>?>?

    @POST("blogs")
    fun blogs(): Call<BlogsResponse>

    @POST("submit-answers")
    fun submitAns(): Call<ResponseBody>

    @FormUrlEncoded
    @POST("add-details")
    fun addDetails(
        @Field("userId") userId: Int,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("address") address: String,
        @Field("dob") dob: String,
    ): Call<ServiceResponse<String>>

    @FormUrlEncoded
    @POST("search-exam")
    fun searchTests(
        @Field("text") text: String
    ): Call<SearchServiceResponse>

    @FormUrlEncoded
    @POST("transactions")
    fun transactions(@Field ("user_id") userId: Int):Call<TransactionResponse>

    @FormUrlEncoded
    @POST("change-password")
    fun changePassword(
        @Field("userId") userId: Int,
        @Field("oldPassword") oldPassword:String,
        @Field("password") password:String,
        @Field("cPassword") cPassword:String )
    :Call <ResetPasswordResponse>
}
