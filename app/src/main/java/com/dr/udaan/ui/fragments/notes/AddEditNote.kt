package com.dr.udaan.ui.fragments.notes

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.dr.udaan.MyApp
import com.dr.udaan.base.BaseFragment
import com.dr.udaan.databinding.FragmentAddEditNoteBinding
import com.dr.udaan.room.Note
import com.dr.udaan.util.AppFunctions
import com.dr.udaan.util.Const
import com.dr.udaan.util.loadByteArray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class AddEditNote : BaseFragment<FragmentAddEditNoteBinding>() {

    private var isEdit = false
    private var _id: String? = null
    private var _imageBytes: ByteArray? = null
    private var note: Note? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _id = arguments?.getString(Const.ID)
        Log.d(TAG, "onViewCreated: $_id")
        isEdit = !_id.isNullOrEmpty()
        if (isEdit) {
            note = _id?.let { MyApp.myDatabase?.noteDao()?.getById(it) }
            if (note == null) {
                isEdit = false
            } else {
                updateUi()
            }
        } else {
            _id = UUID.randomUUID().toString()
        }
        actions()

    }

    private fun actions() {
        binding.saveNoteButton.setOnClickListener {
            validate {
                if (it) {
                    save()
                }
            }
        }
        binding.noteImageView.setOnClickListener {
            pickImage.launch("image/*")
        }
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun updateUi() {
        binding.toolbar.title = note?.title ?: ""
        binding.noteImageView.loadByteArray(note?.imageBytes)
        binding.noteTitleEditText.setText(note?.title ?: "")
        binding.noteContentEditText.setText(note?.content ?: "")
    }

    private fun validate(callback: (isValidate: Boolean) -> Unit) {
        if (binding.noteTitleEditText.text.toString().trim().isEmpty()) {
            binding.noteTitleEditText.error = "Enter title"
            callback(false)
        } else if (binding.noteContentEditText.text.toString().trim().isEmpty()) {
            binding.noteContentEditText.error = "Enter content"
            callback(false)
        } else {
            callback(true)
        }
    }

    private fun save() {
        CoroutineScope(IO).launch {
            if (_id == null) {
                withContext(Main) {
                    Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
                return@launch
            }
            MyApp.myDatabase?.noteDao()?.insert(
                Note(
                    _id!!,
                    title = binding.noteTitleEditText.text.toString(),
                    content = binding.noteContentEditText.text.toString(),
                    imageBytes = _imageBytes,
                    timestamp = System.currentTimeMillis()
                )
            ).also {
                withContext(Main) {
                    findNavController().popBackStack()
                }
            }
        }
    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        _imageBytes = if (uri != null) AppFunctions.uriToByteArray(uri) else null
        binding.noteImageView.loadByteArray(_imageBytes)
    }

    override fun getViewBinding() = FragmentAddEditNoteBinding.inflate(layoutInflater)

}