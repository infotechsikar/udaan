package com.dr.udaan.other


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dr.udaan.api.retrofit.Pojo.Blogdata
import com.dr.udaan.databinding.FragmentBlogsBinding
import com.dr.udaan.ui.BaseFragment


class Blogs : Fragment() {
    private lateinit var blog : Blogdata
   // private lateinit var webView: WebView
    private lateinit var binding:FragmentBlogsBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentBlogsBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        if (arguments!= null){
            blog = arguments?.getParcelable<Blogdata>("blog")!!
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // webView =  binding.webView
        // webView.settings.javaScriptEnabled = true
        // webs()

        val webView = binding.webView
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        val description = blog.description!!
        webView.loadData(description,"text/html", "UTF-8")
    }

}
