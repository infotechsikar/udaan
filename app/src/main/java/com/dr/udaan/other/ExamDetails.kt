package com.dr.udaan.other

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
import com.dr.udaan.databinding.FragmentExamDetailsBinding
import com.dr.udaan.databinding.RowItemExamsBinding

class ExamDetails : Fragment() {
    lateinit var binding: FragmentExamDetailsBinding
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View{
        binding = FragmentExamDetailsBinding.inflate(layoutInflater)
        binding.rv.adapter = AdapterSearchPlaces(findNavController())
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    inner class AdapterSearchPlaces(private val navController: NavController) : RecyclerView.Adapter<AdapterSearchPlaces.PlaceHolder>() {

        inner class PlaceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
            val binding = RowItemExamsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PlaceHolder(binding.root)
        }

        override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
                    holder.itemView.setOnClickListener(){
                        navController.navigate(R.id.questionFragment)
                    }
        }

        override fun getItemCount(): Int {
            return 10
        }

    }
}