package com.dr.udaan.ui.fragments.notes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.dr.udaan.MyApp
import com.dr.udaan.R
import com.dr.udaan.base.BaseFragment
import com.dr.udaan.databinding.FragmentNotesBinding
import com.dr.udaan.util.Const
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Notes : BaseFragment<FragmentNotesBinding>(), PermissionListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        actions()
        askForPermissions()
    }

    private fun setData() {

        CoroutineScope(IO).launch {

            if (MyApp.myDatabase == null) {
                Log.d(TAG, "setData: Database not found")
            }

            val e = MyApp.myDatabase?.noteDao()?.getAll2()
            Log.d(TAG, "setData: ${e?.size}")

            CoroutineScope(Main).launch {
                binding.emptyGhost.isVisible = e.isNullOrEmpty()
                binding.rv.adapter = NotesAdapter() {
                    findNavController().navigate(R.id.addEditNote, Bundle().apply {
                        Log.d(TAG, "setData: ${it.id}")
                        putString(Const.ID, it.id)
                    })
                }.apply {
                    setNotes(e ?: emptyList())
                }
            }
        }
    }

    private fun askForPermissions() {
        Dexter.withContext(mContext)
            .withPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(this)
            .check()
    }

    private fun actions() {
        binding.addNew.setOnClickListener {
            findNavController().navigate(R.id.addEditNote)
        }
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    override fun getViewBinding() = FragmentNotesBinding.inflate(layoutInflater)

    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {

    }

    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
        Toast.makeText(mContext, "Permission denied", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {

    }

}