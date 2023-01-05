package com.dr.udaan.authentication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dr.udaan.databinding.FragmentMoreInformationBinding
import com.dr.udaan.retrofit.AllRequest.AddDetailRequest
import com.dr.udaan.retrofit.Retrofitinstance
import com.dr.udaan.ui.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await

class MoreInformation : BaseFragment<FragmentMoreInformationBinding>() {

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
            if (binding.name.text.toString().isEmpty()){
                binding.name.error = "Enter your name number"
            }
            if (binding.emails.text.toString().isEmpty()){
                binding.emails.error = "Enter your email number"
            }
            if (binding.phoneNumbers.text.toString().isEmpty()){
                binding.phoneNumbers.error = "Enter your phone number"
            }
            if (binding.birth.text.toString().isEmpty()){
                binding.birth.error = "Enter your date of birth here"
            }
            if (binding.categorys.text.toString().isEmpty()){
                binding.categorys.error = "Enter your Category here "
            }
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

    override fun getViewBinding()= FragmentMoreInformationBinding.inflate(layoutInflater)


}