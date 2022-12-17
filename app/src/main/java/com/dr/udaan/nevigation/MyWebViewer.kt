package com.dr.udaan.nevigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.dr.udaan.R
import com.dr.udaan.databinding.ActivityMyWebViewerBinding

class MyWebViewer : AppCompatActivity() {
   lateinit var binding: ActivityMyWebViewerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyWebViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper())
            .post {
//                actions(binding)
//                webViewSettings(binding)
//                updateUi(binding)
            }
    }

}