package com.dr.udaan.ui.activities

import android.annotation.SuppressLint
import android.drm.DrmStore.DrmObjectType.CONTENT
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract.CommonDataKinds.Organization.TITLE
import androidx.appcompat.app.AppCompatActivity
import com.dr.udaan.databinding.ActivityMyWebViewerBinding
import com.dr.udaan.util.Const

class MyWebViewer : AppCompatActivity() {

   lateinit var binding: ActivityMyWebViewerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyWebViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper())
            .post {
                actions(binding)
                webViewSettings(binding)
                updateUi(binding)
            }

    }

    private fun actions(binding: ActivityMyWebViewerBinding) {
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun updateUi(binding: ActivityMyWebViewerBinding) {
        val content = intent?.getStringExtra(Const.CONTENT) ?: ""
        val title = intent?.getStringExtra(Const.TITLE) ?: ""
        binding.toolbar.title = title
        binding.webView.loadData(
             content, "text/html","UTF-8")
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSettings(binding: ActivityMyWebViewerBinding) {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.javaScriptCanOpenWindowsAutomatically = true
    }

}