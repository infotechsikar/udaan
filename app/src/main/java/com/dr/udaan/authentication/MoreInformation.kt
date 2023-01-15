package com.dr.udaan.authentication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.dr.udaan.MyApp
import com.dr.udaan.R
import com.dr.udaan.api.retrofit.Retrofitinstance
import com.dr.udaan.base.BaseActivity
import com.dr.udaan.databinding.FragmentMoreInformationBinding
import com.dr.udaan.room.UserData
import com.dr.udaan.util.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.await
import java.util.*

class MoreInformation : BaseActivity<FragmentMoreInformationBinding>() {

    private var _fromRegister = false
    private var _phoneNumber: String? = null

    override fun init() {
        _fromRegister = intent.getBooleanExtra(Const.IS_FROM_REGISTER, false)
        _phoneNumber = intent.getStringExtra(Const.PHONE) ?: ""
        action()
        updateUi()
    }

    private fun updateUi() {
        binding.back.isVisible = !_fromRegister
        val user = MyApp.myDatabase?.userData()?.getUser()
        binding.etDob.setText(user?.dob ?: "")
        binding.name.setText(user?.name ?: "")
        binding.emails.setText(user?.email ?: "")
        binding.phoneNumbers.setText(user?.mobileNo ?: "")
        binding.categorys.setText("")
        if (_fromRegister && !_phoneNumber.isNullOrEmpty()) {
            binding.phoneNumbers.setText(_phoneNumber)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun action() {

        binding.save.setOnClickListener(){

            if (binding.name.text.toString().isEmpty()){
                binding.name.error = "Enter your name number"
                return@setOnClickListener
            }
            if (binding.emails.text.toString().isEmpty()){
                binding.emails.error = "Enter your email number"
                return@setOnClickListener
            }
            if (binding.phoneNumbers.text.toString().isEmpty()){
                binding.phoneNumbers.error = "Enter your phone number"
                return@setOnClickListener
            }
            if (binding.etDob.text.toString().isEmpty()){
                binding.etDob.error = "Enter your date of birth here"
                return@setOnClickListener
            }
            if (binding.categorys.text.toString().isEmpty()){
                binding.categorys.error = "Enter your category here"
                return@setOnClickListener
            }

            showLoading()

            val userData = if (_fromRegister) UserData().apply {
                id = intent.getIntExtra(Const.ID, -1)
                mobileNo = intent.getStringExtra(Const.PHONE)
            } else MyApp.myDatabase?.userData()?.getUser()
            userData?.name = binding.name.text.toString().trim()
            userData?.email = binding.emails.text.toString().trim()
            userData?.dob = binding.etDob.text.toString().trim()
            userData?.category = binding.categorys.text.toString().trim()

            CoroutineScope(IO).launch {
                if (userData != null) {
                    addDetails(userData)
                }
            }

        }

        binding.back.setOnClickListener {
            finish()
        }

        binding.etDob.setOnClickListener {

            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.etDob.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }

        val categories = arrayOf("Gen", "OBC", "SC", "ST", "Other")
        binding.categorys.setText(categories[0])
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categories)
        binding.categorys.setAdapter(adapter)
        binding.categorys.threshold = 0
        binding.categorys.setOnTouchListener { _, _ ->
            binding.categorys.showDropDown()
            false
        }

    }

    private suspend fun addDetails(userData: UserData) {

        try {
            val response = Retrofitinstance.getRetrofit().addDetails(
                userId = userData.id ?: -1,
                email = userData.email ?: "",
                address = userData.address ?: "",
                dob = userData.dob ?: "",
                name = userData.name ?: ""
            ).await()

            if (response.success == true) {
                Toast.makeText(mContext, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                MyApp.myDatabase?.userData()?.insert(userData)
                if (_fromRegister) {
                    startActivity(Intent(mContext, Login::class.java))
                    finish()
                } else {
                    finish()
                }
            } else {
                Toast.makeText(mContext, response.message ?: "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
            dismissLoading()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun getViewBinding() = FragmentMoreInformationBinding.inflate(layoutInflater)


}