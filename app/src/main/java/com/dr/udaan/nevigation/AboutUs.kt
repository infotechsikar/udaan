package com.dr.udaan.nevigation

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dr.udaan.R
import com.dr.udaan.databinding.ActivityAbutUsBinding
import java.util.*

class AboutUs : AppCompatActivity() {
    private lateinit var binding: ActivityAbutUsBinding
    private lateinit var webView: WebView
    private lateinit var toolbar: Toolbar
    private lateinit var content: String
    private lateinit var title: String
    private lateinit var progressBar: ProgressBar

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abut_us)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
        binding = ActivityAbutUsBinding.inflate(layoutInflater)
        title = Intent().getStringExtra("title").toString()
        content = Intent().getStringExtra("content").toString()
        webView = binding.webview
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        toolbar = findViewById<Toolbar>(R.id.tb)
        progressBar = findViewById<ProgressBar>(R.id.pb)
        setSupportActionBar(toolbar)
        Objects.requireNonNull<ActionBar>(supportActionBar).setDisplayHomeAsUpEnabled(true)
        webView.webViewClient = Client()
        webView.loadDataWithBaseURL(
            null,
            content,
            "text/html", "UTF-8", null
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private class Client : WebViewClient() {
        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
            super.onPageStarted(view, url, favicon)
           // progressBar.setVisibility(View.VISIBLE)
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            //progressBar.setVisibility(View.GONE)
        }
    }
}