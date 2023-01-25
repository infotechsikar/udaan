package com.dr.udaan.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import coil.load
import com.dr.udaan.MyApp
import com.dr.udaan.R
import com.dr.udaan.authentication.Login
import com.dr.udaan.authentication.MoreInformation
import com.dr.udaan.base.BaseFragment
import com.dr.udaan.databinding.FragmentProfileBinding
import com.dr.udaan.ui.activities.MyWebViewer
import com.dr.udaan.util.AppFunctions
import com.dr.udaan.util.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class Profile : BaseFragment<FragmentProfileBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        action()
        updateUi()
    }

    fun action(){
        binding.editProfile.setOnClickListener {
            startActivity(
                Intent(mContext, MoreInformation::class.java)
            )
        }
        binding.changePassword.setOnClickListener {
            findNavController().navigate(R.id.resetPassword)
        }

        binding.helpCenter.setOnClickListener{
            startActivity(
                Intent(
                    mContext,
                    MyWebViewer::class.java
                ).apply {
                    putExtra(Const.CONTENT, AppFunctions.getHelpCenter(mContext))
                    putExtra(Const.TITLE, "Help Center")
                }
            )
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

    private fun updateUi() {
        CoroutineScope(Main).launch {
            binding.name.text = MyApp.myDatabase?.userData()?.getUser()?.name ?: ""
            binding.phoneNumber.text = MyApp.myDatabase?.userData()?.getUser()?.mobileNo ?: ""
            binding.profile.load(MyApp.myDatabase?.userData()?.getUser()?.profileImage ?: R.drawable.user)
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

    override fun getViewBinding() = FragmentProfileBinding.inflate(layoutInflater)

}