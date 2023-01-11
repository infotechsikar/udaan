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
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dr.udaan.R
import com.dr.udaan.adapter.AdapterBlog
import com.dr.udaan.adapter.AdapterCurrentNews
import com.dr.udaan.adapter.AdapterExams
import com.dr.udaan.adapter.AdapterSliderHome
import com.dr.udaan.api.retrofit.Pojo.Blogdata
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
import retrofit2.awaitResponse
import java.lang.Runnable

class Home : BaseFragment<FragmentHomeBinding>() {

    private var handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable
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

        action()
        getSlider()
        CoroutineScope(IO)
            .launch {
                //getSlider()
//                withContext(Main) {
//                    binding.vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//                    val adapter = AdapterSliderHome(binding.vp, sliderImages)
//                    binding.vp.adapter = adapter
//
//                    runnable = kotlinx.coroutines.Runnable {
//                        binding.vp.currentItem = binding.vp.currentItem + 1
//                        handler.postDelayed(runnable, 3000)
//                    }
//                    handler.postDelayed(runnable, 3000)
//                }
                getCategories()
                getBlogs()
            }
        return binding.root
    }

    fun action() {
        binding.viewAll.setOnClickListener() {
            findNavController().navigate(R.id.exam)
        }
        binding.notes.setOnClickListener(){
           // findNavController().navigate(R.id.notes)
        }
        binding.save.setOnClickListener(){
            findNavController().navigate(R.id.saved)
        }
        binding.growth.setOnClickListener(){
            findNavController().navigate(R.id.growth)
        }
        binding.score.setOnClickListener(){
            findNavController().navigate(R.id.scoreBoard)
        }
    }

    private  fun getSlider() {
        binding.vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        list.add(ModelSlider("https://firebasestorage.googleapis.com/v0/b/adhyayan-f5438.appspot.com/o/upsc.png?alt=media&token=9cdb9b2b-3842-461a-badb-559d5e5a6137"))
        list.add(ModelSlider("https://firebasestorage.googleapis.com/v0/b/adhyayan-f5438.appspot.com/o/upsc.png?alt=media&token=9cdb9b2b-3842-461a-badb-559d5e5a6137"))
        list.add(ModelSlider("https://firebasestorage.googleapis.com/v0/b/adhyayan-f5438.appspot.com/o/upsc.png?alt=media&token=9cdb9b2b-3842-461a-badb-559d5e5a6137"))
        val adapter = AdapterCurrentNews(binding.vp, list)
        binding.vp.adapter = adapter
        runnable = Runnable {
            binding.vp.currentItem = binding.vp.currentItem + 1
            handler.postDelayed(runnable, 3000)
        }
        handler.postDelayed(runnable, 3000)
//        try {
//
//            val response = Retrofitinstance.getRetrofit().sliders().awaitResponse()
//
//            if (response.isSuccessful){
//                val slider = response.body()!!.categoryData
//                for (data in slider) {
//                    sliderImages.add(data.sliderImage!!)
//                }
//            }
//
//            else{
//                withContext(Dispatchers.Main){
//                    Toast.makeText(mContext, "failed to load slider please try after some time!", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }

    }

    private suspend fun getBlogs(){
        var bList = ArrayList<Blogdata>()
        val response = Retrofitinstance.getRetrofit().blogs().awaitResponse()
       withContext(Dispatchers.Main){
           if (response.isSuccessful){

               Log.d("Blogg",response.body()!!.blogdata.toString())
               bList = response.body()!!.blogdata
               withContext(Main){
                   binding.rv.adapter = AdapterBlog(bList,findNavController())
               }}

           else {
               Toast.makeText(mContext, "failed to load blog! please try after some time!", Toast.LENGTH_SHORT).show()
           }
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
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)
}