package com.dr.udaan.ui.fragments.blog

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dr.udaan.base.BaseFragment
import com.dr.udaan.databinding.FragmentViewBlogBinding
import com.dr.udaan.util.Const

class ViewBlog : BaseFragment<FragmentViewBlogBinding>() {

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

    override fun getViewBinding()= FragmentViewBlogBinding.inflate(layoutInflater)

}
