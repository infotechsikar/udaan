package com.dr.udaan.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dr.udaan.MyApp
import com.dr.udaan.R
import com.dr.udaan.adapter.AdapterTransactions
import com.dr.udaan.api.retrofit.Pojo.Transactions
import com.dr.udaan.api.retrofit.Retrofitinstance
import com.dr.udaan.base.BaseFragment
import com.dr.udaan.databinding.FragmentHomeBinding
import com.dr.udaan.databinding.FragmentTransactionBinding
import com.dr.udaan.util.AppFunctions
import kotlinx.coroutines.*
import retrofit2.await
import retrofit2.awaitResponse

class Transaction : BaseFragment<FragmentTransactionBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        action()
    }

    private fun action() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        CoroutineScope(Dispatchers.IO)
            .launch {
                updateUi()
            }
    }

    private suspend fun updateUi() {
        try {
            binding.pb2.visibility = View.VISIBLE
            val response = Retrofitinstance.getRetrofit().transactions(2/** AppFunctions.getUserId() must be used instead of static 2 in production */).await()
            if (response.success == true){
                Log.d(TAG, "updateUi: ${response.message}")
                withContext(Dispatchers.Main){
                    binding.pb2.visibility = View.GONE
                    if (response.transactions.isNullOrEmpty()){
                        binding.noData.visibility = View.VISIBLE
                        binding.rv.visibility = View.GONE
                    }
                    else{
                        binding.rv.apply {
                            //  reversed the list to show recent transaction first
                            adapter = AdapterTransactions(response.transactions ?: emptyList(),findNavController())
                        }
                    }
                }
            }

            else {
                withContext(Dispatchers.Main){
                    Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                    binding.pb2.visibility = View.GONE
                }
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getViewBinding() =  FragmentTransactionBinding.inflate(layoutInflater)
}


