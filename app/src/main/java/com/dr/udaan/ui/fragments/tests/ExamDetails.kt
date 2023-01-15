package com.dr.udaan.ui.fragments.tests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dr.udaan.databinding.FragmentExamDetailsBinding
import com.dr.udaan.base.BaseFragment

class ExamDetails : BaseFragment<FragmentExamDetailsBinding>() {

    var categoryId = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View{
        binding = FragmentExamDetailsBinding.inflate(layoutInflater)
        categoryId = arguments?.getInt("category_id")!!
        //binding.rv.adapter = AdapterSearchPlaces(findNavController())
        action()

        return binding.root
    }

    fun action(){
        binding.back.setOnClickListener(){
            findNavController().popBackStack()
        }
    }

    override fun getViewBinding() = FragmentExamDetailsBinding.inflate(layoutInflater)

}