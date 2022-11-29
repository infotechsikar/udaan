package com.dr.udaan.other

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentQuestionBinding

class questionFragment : Fragment() {
    lateinit var binding: FragmentQuestionBinding
    lateinit var mContext: Context
    private var isLike = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentQuestionBinding.inflate(layoutInflater)
        action()
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    fun action() {
//        binding.a.setOnClickListener(){
//            if (binding.a.isClickable){
//                binding.a.setBackgroundColor(R.color.sky_blue)
//            }
//
//            else{
//                binding.a.setBackgroundColor(R.color.white)
//
//            }
//
//            isLike = !isLike
//        }
        binding.a.setOnClickListener(){
            binding.a.setBackgroundResource(R.color.sky_blue);
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}