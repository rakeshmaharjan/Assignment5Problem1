package com.example.assignment5problem1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {



    private val quizQuestionList = ArrayList<Quiz>()

    private lateinit var radioOpt1: RadioButton
    private lateinit var radioOpt2: RadioButton
    private lateinit var radioOpt3: RadioButton

    private lateinit var checkboxOpt1: CheckBox
    private lateinit var checkboxOpt2: CheckBox
    private lateinit var checkboxOpt3: CheckBox

    private var answer1 = ""
    private var answer2 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeQuizQuestionsAnswers()

        onViewInit()
    }

    private fun onViewInit() {
        radioOpt1 = findViewById(R.id.radio_opt1_q1)
        radioOpt2 = findViewById(R.id.radio_opt2_q1)
        radioOpt3 = findViewById(R.id.radio_opt3_q1)

        checkboxOpt1 = findViewById(R.id.checkbox_opt1_q2)
        checkboxOpt2 = findViewById(R.id.checkbox_opt2_q2)
        checkboxOpt3 = findViewById(R.id.checkbox_opt3_q2)
    }

    private fun initializeQuizQuestionsAnswers() {
        val quiz1 = Quiz(
            "A",
            1
        )

        val quiz2 = Quiz(
            "A",
            2
        )

        quizQuestionList.add(0, quiz1)
        quizQuestionList.add(1, quiz2)
    }

    fun onResetButtonClicked(view: View) {

        //reset question 1 options
        radioOpt1.isChecked = false
        radioOpt2.isChecked = false
        radioOpt3.isChecked = false

        //reset question 2 options
        checkboxOpt1.isChecked = false
        checkboxOpt2.isChecked = false
        checkboxOpt3.isChecked = false

        //reset answer variable
        answer1 = ""
        answer2 = ""
    }

    fun onSubmitButtonClicked(view: View) {

        var score = 0

        val current = LocalDateTime.now()

        //get date
        val formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = current.format(formatterDate)

        //get time
        val formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss")
        val time = current.format(formatterTime)

        readAnswer2()

        if (validateAnswer1()) {
            score += 50
        }
        if (validateAnswer2()) {
            score += 50
        }

        val messageToDisplay =
            "Congratulations! You submitted on current Date: $date and Time: $time, You achieved $score %"

        displayDialog(messageToDisplay, "Your Quiz Score is here..")
    }

    private fun displayDialog(message: String, title: String) {
        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(it)
        }

        builder.setMessage(message)!!.setTitle(title)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun validateAnswer1(): Boolean {
        val quiz = quizQuestionList[0] // accessing first quiz
        if (quiz.answer == answer1) {
            return true
        }
        return false
    }

    private fun validateAnswer2(): Boolean {
        val quiz = quizQuestionList[1] // accessing first quiz
        if (quiz.answer == answer2) {
            return true
        }
        return false
    }

    private fun readAnswer2() {
        answer2 = ""
        if (checkboxOpt1.isChecked) {
            answer2 += "A"
        }
        if (checkboxOpt2.isChecked) {
            answer2 += "B"
        }

        if (checkboxOpt3.isChecked) {
            answer2 += "C"
        }

    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_opt1_q1 ->
                    if (checked) {
                        answer1 = "A"
                    }
                R.id.radio_opt2_q1 ->
                    if (checked) {
                        answer1 = "B"
                    }

                R.id.radio_opt3_q1 ->
                    if (checked) {
                        answer1 = "C"
                    }
            }
        }
    }

}