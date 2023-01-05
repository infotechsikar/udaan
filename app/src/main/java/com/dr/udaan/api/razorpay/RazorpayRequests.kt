package com.dr.udaan.api.razorpay

import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

object RazorpayRequests {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.razorpay.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(RazorpayApi::class.java)

    suspend fun getRazorpayToken(request: RazorpayRequest): String? = try {
        api.createOrder(request = request).await().id
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

}