package com.dr.udaan.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object  Retrofitinstance {

    const val bearerToken: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMWM2YjQ0MGMyMzA2MDI0NjE3MjA4NGIxZWYyZjQ5ODI3ZTljMTBhMTNhM2NmOWQ4ZjkzNzc5MTZlNTZmZGRiNTM0NWRhM2YyMmQ5MTIwNzciLCJpYXQiOjE2NDc4NDA3OTguNDQ5Njg5LCJuYmYiOjE2NDc4NDA3OTguNDQ5NjkxLCJleHAiOjE2NzkzNzY3OTguNDQyOTY5LCJzdWIiOiIyNSIsInNjb3BlcyI6W119.i0gfHkfVBOnB4Yy9Y1fwqQ3qlYz0IDYvcDLI7l_BuB4qI_cs43hjuM9Zqa-DnrCAbQXAByQUXGFeyLMkeDTdssYlN2zFTxH2Wa_8ZF49k61lj2MoeFG2EmjjBg4JN8tXVS2MBWGL7vROCLsbMd23h-W7-TECqzYozhiejw-4bAl64le2oOp5wJcSudoc5CnXqan8qBRpZt7r7la7Szu_K5bykr2JQtMES4koWRp2zADGL6R9qT_4L8UkVJ1gq8ulnMP2EOPq2HO1oZBDFZ39jyo36gGjJEBrzPjn0A95_mIBY8DXnxPGOpaiKJMv0amipfsx1KuKBeu-Gya5-i_S6wVe0Ehm305fUDB_jChL-gndMsKKcwIvQpMzQMU-xIs11MSjHM6Ccg8ZrSYtHPNvVLfZPlblshkTUciYbWqfl5uTZTh7Si0FOgdhV8r7z24F_-vlxTZAimMg4ml5u_wmES_ezWVEBiK16NamDkZDLBj7DuJ121nwl107UHt1K9A3gQ1ixCGbl_gM843QiL0gVEVo6ivkt4oaXnXSBWaA3vwq84wh3RigKzeviXuvquBkay92xBs91GaXE9KXGU3C6iK9lmyCN8EGTf66Umdj5c0z76fN263WAHX5O4j1pfJ0q-kUbIKTMF7gFcvwFC6lYubI44o0Hq6r6Y5ElUsmUjQ"

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
         val retrofit = Retrofit.Builder()
             .baseUrl("https://localguider.lineuptechnology.com/api/")
             .addConverterFactory(GsonConverterFactory.create())
             .client(OkHttpClient())
             .build()
         retrofit.create(RetrofitAPI::class.java)
     }
 }