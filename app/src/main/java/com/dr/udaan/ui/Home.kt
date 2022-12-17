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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dr.udaan.R
import com.dr.udaan.adapter.AdapterExams
import com.dr.udaan.databinding.FragmentHomeBinding
import com.dr.udaan.databinding.RowItemBlogBinding
import com.dr.udaan.databinding.RowItemExamBinding
import com.dr.udaan.other.APIData
import com.dr.udaan.retrofit.Pojo.CategoriesResponse
import com.dr.udaan.retrofit.Pojo.CategoryData
import com.dr.udaan.retrofit.RetrofitAPI
import com.dr.udaan.retrofit.Retrofitinstance
import com.dr.udaan.retrofit.Retrofitinstance.getRetrofit
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.NullPointerException
import java.lang.Runnable

class Home : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var mContext: Context
    var handler = Handler(Looper.getMainLooper())
    lateinit var runnable: Runnable
    private val list = ArrayList<ModelSlider>()

    override fun onDestroy() {
        super.onDestroy()
        handler
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.rv.adapter = AdapterBlog()
        slider()
        action()

        CoroutineScope(IO)
            .launch {
                getCategories()
            }
        return binding.root
    }

    fun action() {
        binding.viewAll.setOnClickListener() {
            findNavController().navigate(R.id.exam)
        }
    }
    private fun slider() {
        binding.vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        list.add(ModelSlider("https://firebasestorage.googleapis.com/v0/b/sinfode-5ebe3.appspot.com/o/upsc.png?alt=media&token=1387b999-758b-4c6e-a836-5ad2fc896de5"))
        list.add(ModelSlider("https://firebasestorage.googleapis.com/v0/b/sinfode-5ebe3.appspot.com/o/upsc.png?alt=media&token=1387b999-758b-4c6e-a836-5ad2fc896de5"))
        list.add(ModelSlider("https://firebasestorage.googleapis.com/v0/b/sinfode-5ebe3.appspot.com/o/upsc.png?alt=media&token=1387b999-758b-4c6e-a836-5ad2fc896de5"))
        list.add(ModelSlider("https://firebasestorage.googleapis.com/v0/b/sinfode-5ebe3.appspot.com/o/upsc.png?alt=media&token=1387b999-758b-4c6e-a836-5ad2fc896de5"))
        val adapterCurrentNews = AdapterSlider(binding.vp, list)

        binding.vp.adapter = adapterCurrentNews
        runnable = kotlinx.coroutines.Runnable {
            binding.vp.currentItem = binding.vp.currentItem + 1
            handler.postDelayed(runnable, 3000)
        }

        handler.postDelayed(runnable, 3000)

//        binding.vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                handler.removeCallbacks(runnable)
//                handler.postDelayed(runnable, 2000)
//            }
//        }
//        )
    }

    private fun getCategories() {

        CoroutineScope(IO)
            .launch {

                val categoryData = APIData.fetchCategories()

                val cList = ArrayList<CategoryData>()

                for (i in 0..3) {
                    if (categoryData.size > i) {
                        cList.add(categoryData[i])
                    }
                }
                withContext(Main){
                    binding.cl2.adapter = AdapterExams(cList,findNavController())
                }
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
            return list.size
        }
        }


}