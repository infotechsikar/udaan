package com.dr.udaan.other

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dr.udaan.R
import com.dr.udaan.adapter.AdapterExams
import com.dr.udaan.databinding.FragmentExamDetailsBinding
import com.dr.udaan.databinding.RowItemExamsBinding
import com.dr.udaan.retrofit.AllRequest.TestRequest
import com.dr.udaan.retrofit.Pojo.TestData
import com.dr.udaan.retrofit.Retrofitinstance
import kotlinx.coroutines.*
import retrofit2.await

class ExamDetails : Fragment() {
    lateinit var binding: FragmentExamDetailsBinding
    lateinit var mContext: Context
    var categoryId = -1
    private var allTest = arrayListOf<TestData>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View{
        binding = FragmentExamDetailsBinding.inflate(layoutInflater)
        categoryId = arguments?.getInt("category_id")!!
        //binding.rv.adapter = AdapterSearchPlaces(findNavController())
        action()
        CoroutineScope(Dispatchers.IO)
            .launch {
              test()
            }
        return binding.root
    }

    fun action(){
        binding.back.setOnClickListener(){
            findNavController().popBackStack()
        }
    }



    private suspend fun test(){
        var testData = ArrayList<TestData>()

        val response = Retrofitinstance.getRetrofit().test(categoryId).await()

        testData = response.testData

      //  for (data in response)
        //    data.title?.let { Log.d("SINFO", it) }

        val cList = ArrayList<TestData>()

        for (i in 0..4) {
            if (testData.size > i) {
                cList.add(testData[i])
            }
        }
        withContext(Dispatchers.Main){
            binding.rv.adapter = AdapterTest(cList,findNavController())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    inner class AdapterTest(private val list: ArrayList<TestData>, private val navController: NavController)
        : RecyclerView.Adapter<AdapterTest.TestHolder>() {
        inner class TestHolder(itemView: View, val dBinding:RowItemExamsBinding ) : RecyclerView.ViewHolder(itemView) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestHolder {
            val binding = RowItemExamsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return TestHolder(binding.root,binding)
        }

        override fun onBindViewHolder(holder: TestHolder, position: Int) {

          //  holder.dBinding.title.text = list[position].title
            holder.dBinding.test.text = list[position].categoryId.toString()

            holder.itemView.setOnClickListener {

                val bundle = Bundle()
                bundle.putParcelableArrayList("questionArrayList",list[position].question)
                navController.navigate(R.id.questionFragment,bundle)
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }

    }

    inner class AdapterSearchPlaces(private val navController: NavController) :
     RecyclerView.Adapter<AdapterSearchPlaces.PlaceHolder>() {

        inner class PlaceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
            val binding =
                RowItemExamsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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