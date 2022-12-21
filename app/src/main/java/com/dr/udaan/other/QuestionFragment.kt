package com.dr.udaan.other

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dr.udaan.adapter.AdapterQuestions
import com.dr.udaan.databinding.FragmentQuestionBinding
import com.dr.udaan.retrofit.Pojo.QuestionData
import com.dr.udaan.utils.AppConstants

class QuestionFragment : Fragment() {
    lateinit var binding: FragmentQuestionBinding
    lateinit var mContext: Context
    private lateinit var questionDataList: ArrayList<QuestionData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        questionDataList = arguments?.getParcelableArrayList(AppConstants.QUESTIONS_DATA)!!
        binding = FragmentQuestionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi()
        action()
    }

    private fun updateUi() {
        binding.recyclerView.adapter = AdapterQuestions(questionDataList)
    }

    private fun action(){
        binding.back.setOnClickListener(){
            findNavController().popBackStack()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}