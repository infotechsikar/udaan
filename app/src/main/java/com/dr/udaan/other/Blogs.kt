package com.dr.udaan.other


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dr.udaan.databinding.FragmentBlogsBinding
import com.dr.udaan.api.retrofit.Pojo.Blogdata
import com.dr.udaan.ui.BaseFragment
import com.dr.udaan.util.Const


class Blogs : BaseFragment<FragmentBlogsBinding>() {

    private var title: String? = null
    private var content: String? = null
    private var dateTime: String? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments!= null){
            title = arguments?.getString(Const.TITLE) ?: ""
            content = arguments?.getString(Const.CONTENT) ?: ""
            dateTime = arguments?.getString(Const.DATE_TIME) ?: ""
        }

        actions()
        updateUi()

    }

    private fun updateUi() {
        try {
            binding.webView.settings.javaScriptEnabled = true
            binding.toolbar.title = title ?: ""
            binding.toolbar.subtitle = dateTime ?: ""
            binding.webView.loadData(content ?: "", "text/html", "UTF-8")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun actions() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun getViewBinding()= FragmentBlogsBinding.inflate(layoutInflater)

}
