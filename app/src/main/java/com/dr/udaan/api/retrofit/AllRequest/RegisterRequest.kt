package com.dr.udaan.api.retrofit.AllRequest

data class RegisterRequest(
     val role_id:String,
     val mobile_no:String,
     val password:String,
     val confirm_password:String
        )
