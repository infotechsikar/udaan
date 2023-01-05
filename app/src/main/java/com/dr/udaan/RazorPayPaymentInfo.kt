package com.dr.udaan

import com.dr.udaan.room.UserData

data class RazorPayPaymentInfo(
    val userData: UserData,
    val amount: Int,
    val orderId: String,
    val isStartPayment:Boolean = false
            )