package com.dr.udaan.notes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentNotesViewerBinding
import com.dr.udaan.util.AppConstant.CONTENT
import com.dr.udaan.util.AppConstant.DATE_TIME
import com.dr.udaan.util.AppConstant.DOCUMENT_ID
import com.dr.udaan.util.AppConstant.IMAGE_URL
import com.dr.udaan.util.AppConstant.IS_EDIT_MODE
import com.dr.udaan.util.AppConstant.LABEL
import com.dr.udaan.util.AppConstant.THE_TITLE

class NotesViewer : Fragment() {


    private lateinit var binding : FragmentNotesViewerBinding
    private lateinit var mContext : Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesViewerBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString(THE_TITLE).toString()
        val imageUrl = arguments?.getString(IMAGE_URL).toString()
        val dateTime = arguments?.getString(DATE_TIME).toString()
        val label = arguments?.getString(LABEL).toString()
        val content = arguments?.getString(CONTENT).toString()
        val id = arguments?.getString(DOCUMENT_ID).toString()

        binding.title.text = title
        binding.dateTime.text = dateTime
        binding.label.text = label
        binding.content.text = content
        Glide.with(mContext)
            .load(imageUrl).into(binding.imageView)

        binding.edit.setOnClickListener {
            val args = Bundle()
            args.putString(DOCUMENT_ID, id)
            args.putBoolean(IS_EDIT_MODE, true)
            findNavController().navigate(R.id.notesEditor, args)
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.notes)
        }

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.notes)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

    }


}