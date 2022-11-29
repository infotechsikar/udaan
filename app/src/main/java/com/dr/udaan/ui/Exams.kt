package com.dr.udaan.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentExamsBinding
import com.dr.udaan.databinding.RowItemExamBinding

class Exams : Fragment() {
    lateinit var binding: FragmentExamsBinding
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentExamsBinding.inflate(layoutInflater)
        binding.rv.adapter = AdapterSearchPlaces(findNavController())
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    inner class AdapterSearchPlaces(val navController: NavController) : RecyclerView.Adapter<AdapterSearchPlaces.PlaceHolder>() {

        inner class PlaceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
            val binding =
                RowItemExamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PlaceHolder(binding.root)
        }

        override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
            holder.itemView.setOnClickListener(){
                navController.navigate(R.id.examDetails)
            }

        }

        override fun getItemCount(): Int {
            return 10
        }
    }
}