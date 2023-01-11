package com.dr.udaan.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentProfile2Binding
import com.dr.udaan.databinding.LogoutLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class Profile : Fragment() {

  lateinit var binding: FragmentProfile2Binding
  lateinit var mContext: Context
   lateinit var logoutBinding: LogoutLayoutBinding

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

        binding.cl4.setOnClickListener(){
            val dialog = BottomSheetDialog(mContext)
            logoutBinding = LogoutLayoutBinding.inflate(dialog.layoutInflater)
            dialog.setContentView(logoutBinding.root)

            dialog.show()

            logoutBinding.cancel.setOnClickListener(View.OnClickListener {
                Toast.makeText(mContext, "no ☺️", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            })
            logoutBinding.logout.setOnClickListener(View.OnClickListener {
                Toast.makeText(mContext, " yes \uD83D\uDE1E", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            })
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}