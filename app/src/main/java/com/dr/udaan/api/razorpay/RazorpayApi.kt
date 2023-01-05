package com.dr.udaan.api.razorpay

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface RazorpayApi {

    @POST("v1/orders")
    fun createOrder(
        @Header("Authorization") authorization: String = "Basic YOUR_KEY_ID:YOUR_KEY_SECRET",
        @Body request: RazorpayRequest
    ): Call<RazorpayResponse>

}