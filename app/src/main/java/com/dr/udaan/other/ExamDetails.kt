package com.dr.udaan.other

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dr.udaan.R
import com.dr.udaan.adapter.AdapterExams
import com.dr.udaan.databinding.FragmentExamDetailsBinding
import com.dr.udaan.databinding.RowItemExamsBinding
import com.dr.udaan.api.retrofit.AllRequest.TestRequest
import com.dr.udaan.api.retrofit.Pojo.TestData
import com.dr.udaan.api.retrofit.Retrofitinstance
import com.dr.udaan.ui.BaseFragment
import com.dr.udaan.util.Const
import kotlinx.coroutines.*
import retrofit2.await

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