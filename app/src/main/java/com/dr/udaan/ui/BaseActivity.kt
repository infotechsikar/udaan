package com.dr.udaan.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.dr.udaan.dialogs.Loading

abstract class BaseActivity<T: ViewBinding>() : AppCompatActivity() {

    private lateinit var loading: Loading
    protected lateinit var binding: T

    protected abstract fun getViewBinding(): T

    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        loading = Loading(this).build()
        init()
    }

    fun showLoading() {
        loading.showLoading()
    }

    fun showLoading(msg: String) {
        loading.showLoading(msg)
    }

    fun dismissLoading() {
        loading.dismissLoading()
    }

    fun isLoadingCancelable(isCancelable: Boolean = false) {
        loading.setCancelable(isCancelable)
    }

}