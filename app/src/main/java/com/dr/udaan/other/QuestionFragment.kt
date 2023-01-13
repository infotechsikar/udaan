package com.dr.udaan.other

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Toast
import com.dr.udaan.databinding.FragmentQuestionBinding
import com.dr.udaan.api.retrofit.Pojo.QuestionData
import com.dr.udaan.api.retrofit.Retrofitinstance
import com.dr.udaan.ui.BaseFragment
import com.dr.udaan.util.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class QuestionFragment : BaseFragment<FragmentQuestionBinding>() {

    //private var questionsArrayList = arrayListOf<QuestionData>()
    var testId: Int = -1
    var list = ArrayList<QuestionData>()
    private var position = 0
    val duration = 0
    private var countDownTimer: CountDownTimer? = null
    private val answerList = ArrayList<SubmitAnswer>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        testId = arguments?.getInt(Const.TEST_ID) ?: -1

        binding.heading.text = ""
        actions()
        countDownTimer()

        showLoading()
        CoroutineScope(IO)
            .launch {
                fetchQuestions()
                withContext(Main) {
                    if (list.isNotEmpty()) {
                        loadQuestions()
                    } else {
                        Toast.makeText(mContext, "No questions found", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    private fun submitAns(selectedOption: Int) {
        answerList.add(
            SubmitAnswer(
                questionId = list[position].id,
                selectedOption = selectedOption
            )
        )
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

    private suspend fun fetchQuestions() {

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

    }

    private fun actions() {
        binding.next.setOnClickListener {
            nextQuestion()
        }

        binding.Previous.setOnClickListener {
            previousQuestion()
        }
    }

    private fun loadQuestions() {

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

        webView.loadDataWithBaseURL(
            null, data ?: "",
            "text/html", "UTF-8", null
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