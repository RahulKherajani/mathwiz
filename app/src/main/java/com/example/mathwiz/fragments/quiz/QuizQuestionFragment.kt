/*
* This file contains backend logic for Quiz Generation and
* Validation.
* */

package com.example.mathwiz.fragments.quiz

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mathwiz.MathWiz
import com.example.mathwiz.R
import com.example.mathwiz.databinding.FragmentQuizQuestionBinding
import java.lang.Math.abs

class QuizQuestionFragment : Fragment(), View.OnClickListener {

    // Declaration of variables.
    private var _binding: FragmentQuizQuestionBinding? = null
    private val binding get() = _binding!!
    private var questions = ArrayList<QuestionModel>()
    private var gradeName = MathWiz.userData?.grade
    private var grade = gradeName!!.split(" ")[1]
    private var category = ""
    private var n = 0
    private val totalQuestions = 5
    private var answer = 0
    private var questionNumber = 1;
    private var optionPosition: Int = 0
    private var answerIndex = 0
    private var result = 0
    private var categoryName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentQuizQuestionBinding.inflate(inflater, container, false)
        // Receive Value from Quiz Description Fragment
        categoryName = arguments?.getString("categoryName").toString()
        category = arguments?.getString("categoryName").toString().split(" ")[0]
        createQuestions(grade, category)
        setQuestion()
        binding.option1.setOnClickListener(this)
        binding.option2.setOnClickListener(this)
        binding.option3.setOnClickListener(this)
        binding.option4.setOnClickListener(this)
        binding.submitQuestionButton.setOnClickListener(this)

        // Setting Title for appbar
        activity?.title = "Quiz"
        return binding.root
    }

    // Function executed on submit question
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitQuestionButton.setOnClickListener {

            if (optionPosition == 0) {
                questionNumber += 1

                when {
                    questionNumber <= questions!!.size -> {
                        setQuestion()
                    }

                    // Quiz ended. Navigates to Result fragment
                    else -> {
                        val bundle = bundleOf(
                            "categoryName" to categoryName,
                            "correct" to result,
                            "total" to totalQuestions
                        )
                        findNavController().navigate(
                            R.id.action_QuizQuestionFragment_to_QuizResultFragment,
                            bundle
                        )
                    }
                }
            }
            // Score calculation and Highlighting the correct and incorrect answers
            else {
                val question = questions?.get(questionNumber - 1)
                if (question!!.correctAnswer != optionPosition) {
                    result = result - 1;
                    answerViewIncorrect(optionPosition)
                }
                result = result + 1
                answerViewCorrect(question.correctAnswer)
                if (questionNumber >= questions!!.size) {
                    binding.submitQuestionButton.text = "Finish"
                } else {
                    binding.submitQuestionButton.text = "Go to next question"
                }
                optionPosition = 0
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*
    * Function to create dynamic questions
    * The difficulty of each question depends on the Grade.
    * Example: A Grade Three user will get questions from numbers
    * from 1 to 10 only.
    * */
    private fun createQuestions(grade: String, category: String) {
        for (i in (1..totalQuestions)) {
            var operand1 = 0
            var operand2 = 0
            var operator = ""
            if (grade == "Three") {
                n = 1
            }
            if (grade == "Four") {
                n = 2
            }
            if (grade == "Five") {
                n = 3
            }

            if (category == "Addition") {
                operator = "+"
            }
            if (category == "Subtraction") {
                operator = "-"
            }
            if (category == "Multiplication") {
                operator = "x"
                if (grade == "Three") {
                    n = 1
                } else {
                    n = 2
                }
            }
            if (category == "Division") {
                operator = "/"
                if (grade == "Three") {
                    n = 1
                } else {
                    n = 2
                }
            }

            var factor = Math.pow(10.0, n.toDouble()).toInt()
            operand1 = (1..factor).random()
            operand2 = (1..factor).random()
            if (operand1 < operand2) {
                val temp = operand1
                operand1 = operand2
                operand2 = temp
            }
            if (category == "Division") {
                operand1 = (1..10).random() * operand2
            }

            if (operator == "+") {
                answer = operand1 + operand2
            } else if (operator == "-") {
                answer = operand1 - operand2
            } else if (operator == "/") {
                answer = operand1 / operand2
            }
            if (operator == "x") {
                answer = operand1 * operand2
            }

            var options = ArrayList<Int>()
            options.add(answer)
            options.add(abs(answer - 1))
            options.add(abs(answer + 1))
            options.add(abs(answer + 2))

            options.shuffle()
            answerIndex = findIndex(options, answer)
            questions.add(
                QuestionModel(
                    i,
                    "$operand1 $operator $operand2",
                    options[0].toString(),
                    options[1].toString(),
                    options[2].toString(),
                    options[3].toString(),
                    answerIndex + 1
                )
            )
        }
    }

    // Function to find indedx of the correct answer
    private fun findIndex(arr: ArrayList<Int>, item: Int): Int {
        return arr.indexOf(item)
    }

    //Function to bind the question and its option on screen
    private fun setQuestion() {
        val question = questions!!.get(questionNumber - 1)
        defaultSettings()
        if (questionNumber == questions!!.size) {
            binding.submitQuestionButton.text = "Finish"
        } else {
            binding.submitQuestionButton.text = "Submit"
        }
        binding.progressBar.progress = questionNumber * 20
        binding.questionBox.text = question.question
        binding.option1.text = question.option1
        binding.option2.text = question.option2
        binding.option3.text = question.option3
        binding.option4.text = question.option4
    }

    // Function to get back default UI settings
    private fun defaultSettings() {
        val options = ArrayList<TextView>()
        options.add(0, binding.option1)
        options.add(1, binding.option2)
        options.add(2, binding.option3)
        options.add(3, binding.option4)

        for (option in options) {
            option.typeface = Typeface.DEFAULT
            option.setBackgroundResource(R.drawable.ic_quiz_option)
            option.isClickable = true
        }
    }

    // Function for OnClick Listener
    override fun onClick(p0: View?) {

        when (p0?.id) {
            R.id.option1 -> {
                selected(binding.option1, 1)
            }
            R.id.option2 -> {
                selected(binding.option2, 2)
            }
            R.id.option3 -> {
                selected(binding.option3, 3)
            }
            R.id.option4 -> {
                selected(binding.option4, 4)
            }
        }
    }

    /*
    * Function which displays the correct answer after each
    * question attempted.
    * */
    private fun answerViewCorrect(answer: Int) {
        binding.option1.isClickable = false
        binding.option2.isClickable = false
        binding.option3.isClickable = false
        binding.option4.isClickable = false
        when (answer) {
            1 -> {
                binding.option1.setBackgroundResource(R.drawable.ic_quiz_option_correct)
            }
            2 -> {
                binding.option2.setBackgroundResource(R.drawable.ic_quiz_option_correct)
            }
            3 -> {
                binding.option3.setBackgroundResource(R.drawable.ic_quiz_option_correct)
            }
            4 -> {
                binding.option4.setBackgroundResource(R.drawable.ic_quiz_option_correct)

            }
        }
    }

    /*
  * Function which displays the incorrect answer after each
  * question attempted.
  * */
    private fun answerViewIncorrect(answer: Int) {
        when (answer) {
            1 -> {
                binding.option1.setBackgroundResource(R.drawable.ic_quiz_option_incorrect)
            }
            2 -> {
                binding.option2.setBackgroundResource(R.drawable.ic_quiz_option_incorrect)
            }
            3 -> {
                binding.option3.setBackgroundResource(R.drawable.ic_quiz_option_incorrect)
            }
            4 -> {
                binding.option4.setBackgroundResource(R.drawable.ic_quiz_option_incorrect)
            }
        }
    }

    private fun selected(textview: TextView, selectedOption: Int) {
        defaultSettings()
        optionPosition = selectedOption
        textview.setTypeface(textview.typeface, Typeface.BOLD)
        textview.setBackgroundResource(R.drawable.ic_quiz_option_selected)
    }
}
