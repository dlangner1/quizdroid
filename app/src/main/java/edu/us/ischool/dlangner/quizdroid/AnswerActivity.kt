package edu.us.ischool.dlangner.quizdroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class AnswerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val answer = intent.extras.get("ANSWER_DATA") as Answer

        val correctAnswerLabel = findViewById<TextView>(R.id.correct_answer_label)
        val userAnswerLabel = findViewById<TextView>(R.id.user_answer_label)
        val numCorrectLabel = findViewById<TextView>(R.id.num_correct_label)
        val answerButton = findViewById<Button>(R.id.answer_button)

        val correctAnswerText = "The correct answer is: \n" + answer.correctAnswer
        correctAnswerLabel.text = correctAnswerText

        val userAnswerText = "You answered: \n" + answer.given
        userAnswerLabel.text = userAnswerText

        numCorrectLabel.text = answer.numCorrect.toString()
    }
}