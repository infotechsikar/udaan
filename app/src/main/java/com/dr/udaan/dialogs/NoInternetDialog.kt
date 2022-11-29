package com.dr.udaan.dialogs

import android.app.Dialog
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.dr.udaan.databinding.DialogNoInternetBinding
import kotlin.system.exitProcess

class NoInternetDialog(val mContext: AppCompatActivity) {

    private lateinit var d: Dialog
    private lateinit var binding: DialogNoInternetBinding

    fun build() = run {
        d = Dialog(mContext)
        binding = DialogNoInternetBinding.inflate(d.layoutInflater)
        d.setContentView(binding.root)
        d.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        d.setCancelable(false)
        listeners()
        this
    }

    private fun listeners() {
        binding.okay.setOnClickListener {
            mContext.finishAffinity()
            exitProcess(0)
        }
    }

    fun show() {
        if (this::d.isInitialized)
            d.show()
    }

    fun dismiss() {
        if (this::d.isInitialized)
            d.dismiss()
    }

}