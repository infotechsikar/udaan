package com.dr.udaan.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dr.udaan.adapter.AdapterExams
import com.dr.udaan.databinding.FragmentExamsBinding
import com.dr.udaan.other.APIData
import com.dr.udaan.retrofit.Pojo.CategoryData
import kotlinx.coroutines.*

class Exams : Fragment() {
    lateinit var binding: FragmentExamsBinding
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentExamsBinding.inflate(layoutInflater)
     // binding.rv.adapter = AdapterSearchPlaces(findNavController())

        CoroutineScope(Dispatchers.IO)
            .launch {
              getCategories()
            }

        return binding.root
    }

    private fun getCategories() {
        CoroutineScope(Dispatchers.IO)
            .launch {

                val categoryData = APIData.fetchCategories()

                val cList = ArrayList<CategoryData>()

                for (i in 0..4) {
                    if (categoryData.size > i) {
                        cList.add(categoryData[i])
                    }
                }
                withContext(Dispatchers.Main){
                    binding.rv.adapter = AdapterExams(cList,findNavController())
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

//    inner class AdapterSearchPlaces(private val navController: NavController) :
//        RecyclerView.Adapter<AdapterSearchPlaces.PlaceHolder>() {
//
//        inner class PlaceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        }
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
//            val binding =
//                RowItemExamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//            return PlaceHolder(binding.root)
//        }
//
//        override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
//            holder.itemView.setOnClickListener(){
//                navController.navigate(R.id.examDetails)
//            }
//        }
//
//        override fun getItemCount(): Int {
//            return 10
//        }
//    }

}