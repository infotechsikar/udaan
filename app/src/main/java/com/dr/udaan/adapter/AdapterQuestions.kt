package com.dr.udaan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dr.udaan.databinding.RowItemQuestionBinding
import com.dr.udaan.retrofit.Pojo.QuestionData


class AdapterQuestions(private val list: ArrayList<QuestionData>):
    RecyclerView.Adapter<AdapterQuestions.QuestionHolder>() {
    inner class QuestionHolder(itemView: View, val dBinding: RowItemQuestionBinding) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
        val binding = RowItemQuestionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  QuestionHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return list.size
    }

}