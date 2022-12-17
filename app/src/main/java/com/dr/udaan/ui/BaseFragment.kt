package com.dr.udaan.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding 
import com.dr.udaan.dialogs.Loading
import com.google.firebase.auth.PhoneAuthProvider

abstract class BaseFragment<T: ViewBinding>() : Fragment() {

    private lateinit var loading: Loading
    protected lateinit var binding: T
    open lateinit var mContext: Context

    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
    }

    protected abstract fun getViewBinding(): T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = getViewBinding()
        loading = Loading(mContext).build()
        return binding.root
    }

    fun showLoading() {
        if (this::loading.isInitialized)
            loading.showLoading()
    }

    fun showLoading(msg: String) {
        if (this::loading.isInitialized)
            loading.showLoading(msg)
    }

    fun dismissLoading() {
        if (this::loading.isInitialized)
            loading.dismissLoading()
    }

    fun isLoadingCancelable(isCancelable: Boolean = false) {
        if (this::loading.isInitialized)
            loading.setCancelable(isCancelable)
    }

}