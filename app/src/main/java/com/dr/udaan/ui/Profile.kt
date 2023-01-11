package com.dr.udaan.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentProfile2Binding
import com.dr.udaan.room.MyDatabase
import com.dr.udaan.util.AppFunctions

class Profile : Fragment() {

    lateinit var binding: FragmentProfile2Binding
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentProfile2Binding.inflate(layoutInflater)
        action()
        binding.phoneNumber.text = MyDatabase.getDatabase(mContext).userData().getUserData()[0]
            .mobileNo.toString()
        Toast.makeText(mContext, AppFunctions.getUserName(mContext).toString(), Toast.LENGTH_SHORT).show()
        binding.name.text = AppFunctions.getUserName(mContext).toString()
        return binding.root
    }

    fun action(){
        binding.cl.setOnClickListener(){
            findNavController().navigate(R.id.information)
        }
        binding.cl2.setOnClickListener(){
            findNavController().navigate(R.id.resetPassword)
        }
        binding.cl4.setOnClickListener {
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
        findNavController().popBackStack()
        findNavController().navigate(R.id.login)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}