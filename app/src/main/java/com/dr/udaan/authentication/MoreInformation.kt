package com.dr.udaan.authentication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dr.udaan.databinding.FragmentMoreInformationBinding
import com.dr.udaan.retrofit.AllRequest.AddDetailRequest
import com.dr.udaan.retrofit.Retrofitinstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await

class MoreInformation : Fragment() {
    lateinit var binding: FragmentMoreInformationBinding
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentMoreInformationBinding.inflate(layoutInflater)
        action()
        CoroutineScope(Dispatchers.IO)
            .launch {
              addDetails()
            }
        return binding.root
    }

    private fun action(){
        binding.skip.setOnClickListener() {
            findNavController().popBackStack()
        }
        binding.verify.setOnClickListener(){
            Toast.makeText(mContext, "verification successful", Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun addDetails(){
        val request =AddDetailRequest(
            "1","priya","add@gmail.com","29-5-1990","1","kalyan marg"
        )
        val response = Retrofitinstance.getRetrofit().addDetail(request).await()
        Log.d("INFO",response.data.toString())


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}