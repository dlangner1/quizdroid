package edu.us.ischool.dlangner.quizdroid

import java.io.Serializable

data class Answer(val given: String, val correctAnswer: String, val numCorrect: Int) : Serializable
