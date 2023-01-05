package com.dr.udaan

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.dr.udaan.room.MyDatabase

class MyApp: Application() {

    companion object {
        var appContext: Context? = null
        var myDatabase: MyDatabase? = null
        val razorPayPaymentData = MutableLiveData<RazorPayPaymentInfo>()
    }


    override fun onCreate() {
        super.onCreate()
        appContext = this
        myDatabase = MyDatabase.getDatabase(this)
    }

}