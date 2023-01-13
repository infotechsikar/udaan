package com.dr.udaan.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.dr.udaan.MyApp
import com.dr.udaan.R
import com.dr.udaan.authentication.Login
import com.dr.udaan.databinding.FragmentProfile2Binding
import com.dr.udaan.util.AppFunctions

class Profile : Fragment() {

  lateinit var binding: FragmentProfile2Binding
  lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentProfile2Binding.inflate(layoutInflater)
        action()
        return binding.root
    }

    fun action(){
        binding.cl.setOnClickListener(){
            findNavController().navigate(R.id.information)
        }
        binding.cl2.setOnClickListener(){
            findNavController().navigate(R.id.resetPassword)
        }
        binding.logOut.setOnClickListener {
            AlertDialog.Builder(mContext)
                .setTitle("Logout")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes") {_,_->
                    logOut()
                }.setNegativeButton("No", null).create().show()
        }
    }

    private fun logOut() {
        AppFunctions.logOut(mContext)
        MyApp.myDatabase?.userData()?.deleteAll()
        startActivity(Intent(mContext, Login::class.java))
        activity?.finish()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}