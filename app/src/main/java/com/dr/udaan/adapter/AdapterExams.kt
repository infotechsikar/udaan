package com.dr.udaan.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dr.udaan.R
import com.dr.udaan.databinding.RowItemExamBinding
import com.dr.udaan.api.retrofit.Pojo.CategoryData
import com.dr.udaan.api.retrofit.Pojo.TestData
import com.dr.udaan.util.Const

class AdapterExams(private val list: ArrayList<CategoryData>, val callback: (examId: Int) -> Unit):
        RecyclerView.Adapter<AdapterExams.ExamHolder>() {

        inner class ExamHolder(itemView: View, val dBinding: RowItemExamBinding)
            : RecyclerView.ViewHolder(itemView) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamHolder {
            val binding = RowItemExamBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return  ExamHolder(binding.root, binding)
        }

        override fun onBindViewHolder(holder: ExamHolder, position: Int) {
            holder.dBinding.coursesName.text = list[position].categoryName

            holder.itemView.setOnClickListener {
                callback(list[position].id ?: -1)
            }
        }

        override fun getItemCount(): Int {
           return list.size
        }

    }