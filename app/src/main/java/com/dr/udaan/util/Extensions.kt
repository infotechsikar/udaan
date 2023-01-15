package com.dr.udaan.util

import android.graphics.BitmapFactory
import android.widget.ImageView
import coil.load
import com.dr.udaan.R

fun ImageView.loadByteArray(imageInByte: ByteArray?) {
    if (imageInByte == null) {
        this.load(R.drawable.ic_image_256)
    } else {
        val bitmap = BitmapFactory.decodeByteArray(imageInByte, 0, imageInByte.size)
        setImageBitmap(bitmap)
    }
}