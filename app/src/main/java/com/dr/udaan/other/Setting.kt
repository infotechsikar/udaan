package com.dr.udaan.other

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentSettingBinding
import com.dr.udaan.util.AppFunctions.isNotificationOn
import com.dr.udaan.util.AppFunctions.setNotificationOn

class Setting : Fragment() {
    lateinit var binding: FragmentSettingBinding
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentSettingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadPreviousData()
        actions()

    }

    private fun actions() {

        binding.back.setOnClickListener {
            findNavController().popBackStack(R.id.settings,true)
        }

        binding.editProfile.setOnClickListener {
            findNavController().navigate(R.id.editProfile)
        }
        binding.logOut.setOnClickListener {
            logOut()
        }

        binding.switchNotification.setOnClickListener {
            setNotificationOn(mContext, binding.switchNotification.isChecked)
        }
    }

    private fun logOut() {
        AlertDialog.Builder(mContext)
            .setTitle("Logout")
            .setMessage("Are you sure you want to log out?")
            .setPositiveButton("Yes") {_,_->
                logOut()
            }.setNegativeButton("No", null).create().show()
    }
    private fun loadPreviousData() {
        binding.switchNotification.isChecked = isNotificationOn(mContext)
    }

}