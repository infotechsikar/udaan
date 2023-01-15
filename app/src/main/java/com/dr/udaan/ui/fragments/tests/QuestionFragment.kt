package com.dr.udaan.ui.fragments.tests

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.dr.udaan.R
import com.dr.udaan.databinding.FragmentQuestionBinding
import com.dr.udaan.api.retrofit.Pojo.QuestionData
import com.dr.udaan.api.retrofit.Retrofitinstance
import com.dr.udaan.base.BaseFragment
import com.dr.udaan.util.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await
import kotlin.math.log

class QuestionFragment : BaseFragment<FragmentQuestionBinding>() {

    var testId: Int = -1
    var title = ""
    var list = ArrayList<QuestionData>()
    private var position = 0
    var duration = 0
    private var countDownTimer: CountDownTimer? = null
    private val answerList = HashSet<SubmitAnswer>()
    private var isReview = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        testId = arguments?.getInt(Const.TEST_ID, -1) ?: -1
        title = arguments?.getString(Const.TITLE, "") ?: ""
        duration = arguments?.getInt(Const.DURATION, 0) ?: 0
        isReview = arguments?.getBoolean(Const.IS_REVIEW, false ) ?: false

        binding.heading.text = title

        actions()

        showLoading()

        CoroutineScope(IO)
            .launch {
                fetchQuestions().also {
                    withContext(Main) {
                        if (it.isNotEmpty()) {
                            if (isReview) {
                                getAnswers().also {
                                    loadQuestions()
                                    countDownTimer()
                                }
                            } else {
                                loadQuestions()
                                countDownTimer()
                            }
                        } else {
                            Toast.makeText(mContext, "No questions found", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
    }

    private fun getAnswers(): Set<SubmitAnswer> {
        return answerList
    }

    private fun submitAns(selectedOption: Int) {
        answerList.add(
            SubmitAnswer(
                questionId = list[position].id,
                selectedOption = selectedOption
            )
        )
    }

    private fun submitTest() {
        submitConfirmations {
            if (it){
                Toast.makeText(mContext, "Test submitted", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.scoreBoard, null, NavOptions.Builder().setPopUpTo(R.id.scoreBoard, true).build())
            }
        }
    }

    private fun submitConfirmations(callback: (isSubmit: Boolean) -> Unit) {
        AlertDialog.Builder(mContext)
            .setTitle("Submit")
            .setMessage("Are you sure you want to submit this test?")
            .setPositiveButton("Yes"){_,_->
                callback(true)
            }.setNegativeButton("No"){_,_->
                callback(false)
            }.create().show()
    }

    private fun updateUi() {

        if (position == list.lastIndex) {
            binding.next.text = "Submit"
        } else {
            binding.next.text = "Next"
        }

        binding.Previous.visibility = if (position != 0) View.VISIBLE else View.INVISIBLE
        binding.Previous.isEnabled = position != 0

        try {
            val itemAtSelection =
                binding.optionsContainer.getChildAt(getSelectedOption())
            Log.d(TAG, "updateUi: $itemAtSelection")
            binding.optionsContainer.children.mapIndexed { index, view ->
                if (index != 0) {
                    if (view == itemAtSelection) {
                        (view as LinearLayout).setBackgroundResource(R.drawable.bg_selected_item)
                    } else {
                        (view as LinearLayout).setBackgroundResource(R.drawable.back_img_library)
                    }
                }
            }
        } catch (e: Exception) {
            binding.optionsContainer.children.mapIndexed { index, view ->
                if (index != 0) {
                    (view as LinearLayout).setBackgroundResource(R.drawable.back_img_library)
                }
            }
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun getSelectedOption(): Int {
        val selectedOption = answerList.find {
            it.questionId == list[position].id
        }
        return selectedOption?.selectedOption ?: -1
    }

    @SuppressLint("SetTextI18n")
    private fun updateTime(secondsUntilFinished: Int) {

        val seconds = String.format("%02d", secondsUntilFinished % 60)
        val minutes = String.format("%02d", (secondsUntilFinished - seconds.toInt()) / 60)

        binding.timer.text = "$minutes:$seconds"

    }

    private fun countDownTimer() {
        countDownTimer?.cancel()

        countDownTimer = object : CountDownTimer(duration * 1000L, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                updateTime((millisUntilFinished / 1000).toInt())
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.timer.text = "Times Up"
            }
        }
    }

    private suspend fun fetchQuestions(): ArrayList<QuestionData> {

        try {
            val response = Retrofitinstance.getRetrofit().question(testId)?.await()
            list = ArrayList(response?.question_data ?: emptyList())
            withContext(Main) {
                dismissLoading()
            }
        } catch (e: Exception) {
            Log.d("TAG", "fetchQuestions: ${e.message}")
            e.printStackTrace()
            withContext(Main) {
                dismissLoading()
            }
        }
        return list
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun actions() {

        binding.next.setOnClickListener {
            if (position == list.lastIndex) {
                submitTest()
            } else {
                nextQuestion()
            }
        }

        binding.Previous.setOnClickListener {
            previousQuestion()
        }
        
        binding.optionsContainer.children.mapIndexed { index, view ->
            /**
             * Because at position 0 we found question and we should not add click listener for question
             */
            if (index != 0) {
                val wv = ((view as LinearLayout).getChildAt(0) as WebView)
                wv.setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_UP) {
                        // Handle the click event here
                        submitAns(index)
                        Log.d(TAG, "actions: index: $index answers: ${answerList.size} ${ArrayList(answerList)[0].questionId} ${ArrayList(answerList)[0].selectedOption}")
                        updateUi()
                    }
                    false
                }
//                wv.isClickable = false
//                wv.isFocusable = false
//                view.setOnClickListener {
//
//                }
            }
        }

    }

    private fun loadQuestions() {

        updateUi()

        val question = list[position].question
        loadInWebView(binding.question, question)
        val op1 = list[position].optionA
        loadInWebView(binding.optionA, op1)
        val op2 = list[position].optionB
        loadInWebView(binding.optionB, op2)
        val op3 = list[position].optionC
        loadInWebView(binding.optionC, op3)
        val op4 = list[position].optionD
        loadInWebView(binding.optionD, op4)

    }

    private fun loadInWebView(webView: WebView, data: String?) {

        webView.loadData(
            data ?: "",
            "text/html", "UTF-8"
        )

    }

    private fun nextQuestion() {

        if (position < list.lastIndex) {
            position++
            loadQuestions()
        }
    }

    private fun previousQuestion() {
        if (position > 0) {
            position--
            loadQuestions()
        }
    }

    override fun getViewBinding() = FragmentQuestionBinding.inflate(layoutInflater)

}

data class SubmitAnswer(
    var questionId: Int?,
    var selectedOption: Int?
)