package com.dr.udaan.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dr.udaan.R
import com.dr.udaan.adapter.AdapterExams
import com.dr.udaan.adapter.AdapterSliderHome
import com.dr.udaan.databinding.FragmentHomeBinding
import com.dr.udaan.databinding.RowItemBlogBinding
import com.dr.udaan.other.APIData
import com.dr.udaan.api.retrofit.Pojo.CategoryData
import com.dr.udaan.api.retrofit.Pojo.SliderData
import com.dr.udaan.api.retrofit.Retrofitinstance
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.await
import java.lang.Runnable

class Home : BaseFragment<FragmentHomeBinding>() {

    var handler = Handler(Looper.getMainLooper())
    lateinit var runnable: Runnable
    private val list = ArrayList<ModelSlider>()
    private var sliderImages = arrayListOf<String>()

    override fun onDestroy() {
        if (this::runnable.isInitialized) {
            handler.removeCallbacks(runnable)
        }
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.rv.adapter = AdapterBlog()
        action()

        CoroutineScope(IO)
            .launch {
                getSlider()
                withContext(Main) {
                    binding.vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    val adapter = AdapterSliderHome(binding.vp, sliderImages)
                    binding.vp.adapter = adapter

                    runnable = kotlinx.coroutines.Runnable {
                        binding.vp.currentItem = binding.vp.currentItem + 1
                        handler.postDelayed(runnable, 3000)
                    }
                    handler.postDelayed(runnable, 3000)
                }
                getCategories()
            }
        return binding.root
    }

    fun action() {
        binding.viewAll.setOnClickListener() {
            findNavController().navigate(R.id.exam)
        }
    }


    private suspend fun getSlider() {

        try {

            val response = Retrofitinstance.getRetrofit().sliders().await().categoryData

            for (data in response) {
                sliderImages.add(data.sliderImage!!)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun getCategories() {

        try {

            val categoryData = APIData.fetchCategories()
            val cList = ArrayList<CategoryData>()

            for (i in 0..3) {
                if (categoryData.size > i) {
                    cList.add(categoryData[i])
                }
            }

            withContext(Main) {
                binding.cl2.adapter = AdapterExams(cList, findNavController())
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    inner class AdapterBlog() :
        RecyclerView.Adapter<AdapterBlog.PlaceHolder>() {
        inner class PlaceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
            val binding =
                RowItemBlogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PlaceHolder(binding.root)
        }

        override fun onBindViewHolder(holder: PlaceHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return 10
        }
    }

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

}