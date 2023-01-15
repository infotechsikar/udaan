package com.dr.udaan.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.adapter.AdapterExams
import com.dr.udaan.databinding.FragmentExamsBinding
import com.dr.udaan.other.APIData
import com.dr.udaan.api.retrofit.Pojo.CategoryData
import com.dr.udaan.ui.homepage.ShimmerAdapterBlogs
import com.dr.udaan.ui.homepage.ShimmerAdapterExams
import com.dr.udaan.util.Const
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class Exams: Fragment() {

    lateinit var binding: FragmentExamsBinding
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentExamsBinding.inflate(layoutInflater)

        CoroutineScope(Dispatchers.IO)
            .launch {
              getCategories()
            }

        return binding.root
    }

    private fun getCategories() {

        binding.rv.adapter = ShimmerAdapterExams(10)

        CoroutineScope(Dispatchers.IO)
            .launch {

                val categoryData = APIData.fetchCategories()

                withContext(Main){
                    binding.rv.adapter = AdapterExams(categoryData) {
                        val args = Bundle()
                        args.putInt(Const.EXAM_ID, it)
                        findNavController().navigate(R.id.tests,args)
                    }
                }

            }}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}

