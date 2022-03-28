/*
* This file contains Question Model.
* Each question will have a unique id,
* question string dynamically generated,
* four options, and a correct answer.
* */

package com.example.mathwiz.fragments.quiz

data class QuestionModel(
    val id: Int,
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctAnswer: Int
)
