package com.dr.udaan.ui.homepage

import androidx.recyclerview.widget.RecyclerView.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dr.udaan.databinding.ShimmerItemExamBinding

class ShimmerAdapterExams(private val items: Int): Adapter<ShimmerAdapterExams.ExamHolder>() {

    inner class ExamHolder(itemView: View, val dBinding: ShimmerItemExamBinding)
        : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamHolder {
        val binding = ShimmerItemExamBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ExamHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ExamHolder, position: Int) {
        holder.dBinding.shimmerView.startShimmer()
    }

    override fun getItemCount() = items

}