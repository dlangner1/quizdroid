package edu.us.ischool.dlangner.quizdroid

import java.io.Serializable

data class Question(val text: String, val possibleAnswers: List<String>, val correctAnswer: Int) : Serializable
