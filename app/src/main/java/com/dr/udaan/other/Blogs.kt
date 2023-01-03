package com.dr.udaan.other

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentBlogsBinding
import com.dr.udaan.retrofit.Pojo.Blogdata
import com.dr.udaan.ui.BaseFragment

class Blogs : BaseFragment<FragmentBlogsBinding>() {
    private lateinit var blog : Blogdata

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        if (arguments!= null){
            blog = arguments?.getParcelable<Blogdata>("blog")!!
        }
        return inflater.inflate(R.layout.fragment_blogs, container, false)
    }

    override fun getViewBinding()= FragmentBlogsBinding.inflate(layoutInflater)

}