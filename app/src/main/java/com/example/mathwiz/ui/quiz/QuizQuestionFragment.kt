package com.example.mathwiz.ui.quiz

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mathwiz.R
import com.example.mathwiz.databinding.FragmentQuizQuestionBinding
import java.lang.Math.abs

class QuizQuestionFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentQuizQuestionBinding? = null
    private val binding get() = _binding!!
    private var questions = ArrayList<QuestionModel>()
    private val grade = 3
    private var category = "Addition"
    private var n = 0
    private val totalQuestions = 5
    private var answer = 0
    private var questionNumber = 1;
    private var optionPosition: Int = 0
    private var answerIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentQuizQuestionBinding.inflate(inflater, container, false)
        createQuestions(grade, category)
        setQuestion()
        binding.option1.setOnClickListener(this)
        binding.option2.setOnClickListener(this)
        binding.option3.setOnClickListener(this)
        binding.option4.setOnClickListener(this)
        binding.submitQuestionButton.setOnClickListener(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitQuestionButton.setOnClickListener {

            if (optionPosition == 0) {
                questionNumber += 1

                when {
                    questionNumber <= questions!!.size -> {
                        setQuestion()
                    }
                    else -> {
                        findNavController().navigate(R.id.action_QuizQuestionFragment_to_QuizResultFragment)
                    }
                }
            } else {
                val question = questions?.get(questionNumber - 1)
                if (question!!.correctAnswer != optionPosition) {
                    answerViewIncorrect(optionPosition)
                }
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

    private fun createQuestions(grade: Int, category: String) {

        for (i in (1..totalQuestions)) {
            var operand1 = 0
            var operand2 = 0
            var operator = ""
            if (grade == 3) {
                n = 1
            }
            if (grade == 4) {
                n = 2
            }
            if (grade == 5) {
                n = 3
            }

            if (category === "Addition") {
                operator = "+"
            }
            if (category === "Subtraction") {
                operator = "-"
            }
            if (category === "Multiplication") {
                operator = "*"
                if (grade === 3) {
                    n = 1
                } else {
                    n = 2
                }
            }
            if (category === "Division") {
                operator = "/"
                if (grade === 3) {
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
            if (category === "Division") {
                operand1 = (1..10).random() * operand2
            }

            if (operator === "+") {
                answer = operand1 + operand2
            } else if (operator === "-") {
                answer = operand1 - operand2
            } else if (operator === "/") {
                answer = operand1 / operand2
            }
            if (operator === "*") {
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

    private fun findIndex(arr: ArrayList<Int>, item: Int): Int {
        return arr.indexOf(item)
    }

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

    private fun defaultSettings() {
        val options = ArrayList<TextView>()
        options.add(0, binding.option1)
        options.add(1, binding.option2)
        options.add(2, binding.option3)
        options.add(3, binding.option4)

        for (option in options) {
            option.typeface = Typeface.DEFAULT
            option.setBackgroundResource(R.drawable.ic_quiz_option)
        }
    }

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

    private fun answerViewCorrect(answer: Int) {
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
