package models

import java.io.Serializable

data class Answer(val given: String, val correctAnswer: String,
                  val numCorrect: Int, val nextQuestionNum: Int, val totalQuestionNum: Int) : Serializable
