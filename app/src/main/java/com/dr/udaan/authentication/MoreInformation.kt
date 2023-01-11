package com.dr.udaan.authentication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dr.udaan.databinding.FragmentMoreInformationBinding
import com.dr.udaan.api.retrofit.Retrofitinstance
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

        return binding.root
    }

    private fun action(){
        binding.skip.setOnClickListener() {
            findNavController().popBackStack()
        }

        binding.verify.setOnClickListener(){

            if (verifyData()){
                addDetails()
            }
            else{
                Log.d("ERROR","ERROR")
            }


        }
    }

    private fun verifyData() : Boolean {
        if (binding.name.text.toString().isEmpty()){
            binding.name.error = "Enter your name number"
            return false
        }
        if (binding.emails.text.toString().isEmpty()){
            binding.emails.error = "Enter your email number"
            return false
        }
        if (binding.phoneNumbers.text.toString().isEmpty()){
            binding.phoneNumbers.error = "Enter your phone number"
            return false
        }
        if (binding.birth.text.toString().isEmpty()){
            binding.birth.error = "Enter your date of birth here"
            return false
        }
        if (binding.categorys.text.toString().isEmpty()){
            binding.categorys.error = "Enter your Category here "
            return  false
        }

        else {
            Toast.makeText(mContext, "Verification successful", Toast.LENGTH_SHORT).show()
            return true
        }
    }

    private fun addDetails(){

        CoroutineScope(Dispatchers.IO).launch {
            val response = Retrofitinstance.getRetrofit().addDetails(
                26,binding.name.text.toString(),binding.emails.text.toString(),binding.birth.text.toString(),binding.categorys.text.toString().toInt(),"bhilwara").await()

            Log.d("INFO",response.data.toString())
        }

//        val request = AddDetailRequest(26,binding.name.text.toString(),binding.emails.text.toString(),
//            binding.birth.text.toString(),binding.categorys.text.toString().toInt(),"jaipur")


    }

    override fun getViewBinding()= FragmentMoreInformationBinding.inflate(layoutInflater)

}