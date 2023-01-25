package com.dr.udaan

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
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
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(p0: Activity, p1: Bundle?) {

            }

            override fun onActivityStarted(p0: Activity) {

            }

            override fun onActivityResumed(p0: Activity) {

            }

            override fun onActivityPaused(p0: Activity) {

            }

            override fun onActivityStopped(p0: Activity) {

            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

            }

            override fun onActivityDestroyed(p0: Activity) {

            }

        })
    }

}