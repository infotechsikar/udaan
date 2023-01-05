package com.dr.udaan.api.razorpay

data class RazorpayResponse(
    val id: String,
    val entity: String,
    val amount: Int,
    val amount_paid: Int,
    val amount_due: Int,
    val currency: String,
    val receipt: String,
    val offer_id: String?,
    val status: String,
    val attempts: Int,
    val notes: List<Any>,
    val created_at: Int
)