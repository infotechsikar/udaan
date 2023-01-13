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

class AdapterExams(private val list: ArrayList<CategoryData>, private val navController: NavController):
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

//            Glide.with(holder.itemView.context)
//                .load(list[position].categoryImage)
//                .into(holder.dBinding.imgExam)

            holder.itemView.setOnClickListener {
                val categoryId = list[position].id
                val args = Bundle()
                if (categoryId != null) {
                    args.putInt(Const.EXAM_ID, categoryId)
                }
                navController.navigate(R.id.tests,args)
            }
        }

        override fun getItemCount(): Int {
           return list.size
        }

    }