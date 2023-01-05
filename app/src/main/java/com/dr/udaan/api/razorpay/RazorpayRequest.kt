package com.dr.udaan.api.razorpay

data class RazorpayRequest(
    val amount: Int,
    val currency: String,
    val receipt: String,
    val partial_payment: Boolean,
    val first_payment_min_amount: Int
)