package com.dr.udaan.other


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dr.udaan.databinding.FragmentBlogsBinding
import com.dr.udaan.databinding.FragmentQuestionBinding
import com.dr.udaan.retrofit.Pojo.Blogdata
import com.dr.udaan.ui.BaseFragment


class Blogs : BaseFragment<FragmentBlogsBinding>() {
    private lateinit var blog : Blogdata
   // private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        if (arguments!= null){
            blog = arguments?.getParcelable<Blogdata>("blog")!!
        }
      // webView =  binding.webView
      // webView.settings.javaScriptEnabled = true
      // webs()

        val webView = binding.webView
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.loadUrl("https://udaan.sanwarjangid.com/api/blogs")
        return inflater.inflate(com.dr.udaan.R.layout.fragment_blogs, container, false)
    }

//    private fun webs(){
//    webView = binding.webView
//        webView.webViewClient = object : WebViewClient(){
//
//            @Deprecated("Deprecated in Java")
//            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                if (url != null) {
//                    view?.loadUrl(url)
//                }
//            return true
//        }
//        }
//        webView.loadUrl("https://udaan.sanwarjangid.com/api/blogs")
//
//    }

    override fun getViewBinding()= FragmentBlogsBinding.inflate(layoutInflater)

}
