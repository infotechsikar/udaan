package com.dr.udaan.other

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentExamDetailsBinding
import com.dr.udaan.databinding.RowItemExamsBinding
import com.dr.udaan.retrofit.Pojo.TestData
import com.dr.udaan.retrofit.Retrofitinstance
import com.dr.udaan.utils.AppConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class ExamDetails : Fragment() {
    lateinit var binding: FragmentExamDetailsBinding
    lateinit var mContext: Context
    var categoryId = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View{
        binding = FragmentExamDetailsBinding.inflate(layoutInflater)
        categoryId = arguments?.getInt(AppConstants.CATEGORY_ID) ?: -1
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi()


        action()
    }

    private fun updateUi() {
        val job = lifecycleScope.launch(Dispatchers.IO){
            val response = Retrofitinstance.getRetrofit().getTestData(categoryId)!!.await()!!

            withContext(Dispatchers.Main){
                binding.rv.adapter = AdapterSearchPlaces(response.testData)
            }


        }

    }

    fun action(){
        binding.back.setOnClickListener(){
            findNavController().popBackStack()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    inner class AdapterSearchPlaces(private val list : ArrayList<TestData>) : RecyclerView.Adapter<AdapterSearchPlaces.PlaceHolder>() {
        inner class PlaceHolder(itemView: View, val dBinding: RowItemExamsBinding) : RecyclerView.ViewHolder(itemView) {}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
            val dBinding = RowItemExamsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PlaceHolder(dBinding.root,dBinding)
        }

        override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
            holder.dBinding.title.text = list[position].title
            holder.itemView.setOnClickListener(){
                val args = Bundle()
                args.putParcelableArrayList(AppConstants.QUESTIONS_DATA,list[position].question)
                findNavController().navigate(R.id.questionFragment)
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }

    }
}