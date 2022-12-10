package com.dr.udaan.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object  Retrofitinstance {

    const val bearerToken: String = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNjE3NWFmMzA3NDViN2ZhNWRkYWU0MGRkMTRhMzVkNjg2YTBiNWIzYjRhYjZkZmNiNTZkNWI1N2I5ZDJkMGVjMDRiYzllZTM2NTY3NTg2NWMiLCJpYXQiOjE2NDczNDg2NzguMzc5OTA3LCJuYmYiOjE2NDczNDg2NzguMzc5OTA5LCJleHAiOjE2Nzg4ODQ2NzguMzc1MTEyLCJzdWIiOiIxOSIsInNjb3BlcyI6W119.uwrCV3TL5gYdGGwdviQhm8ixHNMPcExsbgzV-SsyvdWwuNrPT2eEO0o2egQKgRH_NQekQC-6ErsAGdfEy5_il5exJMAlUpXJ_CmnIqeTEIHJBLtFkt4WaoDbAXIy2uWpaejkNMfycegv5LJYDEoHNmEWWRxSVJnQk6choUgVy2UzO_nKQKslrIiLcDWbkKMplkWRJFsdCdsvgeDdVq46Bdo_0cul9ZvgWVMkQsu_XaVCsW4rDXbu5MLQxy2Jk9IaGxTpT8Qwu1g92m6Jk0xrAPpQ0QZfHGZQ5zFUz8n0j-t1oogM_a9GjJ3LEqndDKGplzvZDEGumspqgwnCzfNrCV5lRTYPnevOs_t-Rr7ljlhGrpuVnu5T0jKL0oHX4k5FZaU6RPPKCzXzIV_RJpPtin-rO_VzbOwm7eYD5P4vFvIFqXUhioFvs3J8ot1cFdzxOrsRPVKcUBVLgfXe0K6uzugkm7h7tnGynlsP_5bsoD7YhVWQ7KgO5QXs2Y5OZ6N2clCRBwNEzCM6tfjhAAaHLWGXfbF2HkDhzaEWZ0GTHAPgVHKXxUFv-5vJuzFSpVmXx7010ettm0JUwRe1WTd6DQ3YpSxeVCSgrBH60LE-7BEy5Mtb59caHHEYoaB3faNuo6pj9YsyA7gHpgA7fSAPkY5zW0b2Mgn1tMkUETIqUIs"

    private var okHttpClient: OkHttpClient? = null

    private fun initOkHttpClient() {
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        httpClient.addInterceptor(interceptor)

        httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "bearer $bearerToken")
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        })
        okHttpClient = httpClient.build()
    }

     fun getRetrofit() = run {
         initOkHttpClient()
         val retrofit = Retrofit.Builder()
             .baseUrl("https://udaan.sanwarjangid.com/api/")
             .addConverterFactory(GsonConverterFactory.create())
             .client(okHttpClient)
             .build()
         retrofit.create(RetrofitAPI::class.java)
     }

 }