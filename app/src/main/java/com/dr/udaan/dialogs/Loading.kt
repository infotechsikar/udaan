package com.dr.udaan.dialogs

import android.app.Dialog
import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import com.dr.udaan.R

class Loading(val mContext: Context) {
    private lateinit var d: Dialog

    fun build() = run {
        d = Dialog(mContext)
        d.setContentView(R.layout.dialog_loading)
        d.window?.setLayout(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        setCancelable()
        this
    }

    fun changeTitle(title: String) {
        if (this::d.isInitialized) {
            val mTitle = d.findViewById<TextView>(R.id.message)
            mTitle.text = title
        }
    }

    fun showLoading() {
        if (this::d.isInitialized)
            d.show()
    }

    fun showLoading(msg: String) {
        if (this::d.isInitialized) {
            val mTitle = d.findViewById<TextView>(R.id.message)
            mTitle.text = msg
            d.show()
        }
    }

    fun dismissLoading() {
        if (this::d.isInitialized)
            d.dismiss()
    }

    fun setCancelable(isCancelable: Boolean = false) {
        if (this::d.isInitialized)
            d.setCancelable(isCancelable)
    }

}