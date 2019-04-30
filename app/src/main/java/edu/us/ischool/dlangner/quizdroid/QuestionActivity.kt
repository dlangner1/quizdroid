package edu.us.ischool.dlangner.quizdroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView

class QuestionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val questions = intent.extras.get("QUESTION_DATA") as ArrayList<Question>
        val currentQuestionNum = intent.extras.get("QUESTION_NUM") as Int

        val currentQuestion = questions[currentQuestionNum]

        populateQuestionView(currentQuestion)
    }

    private fun populateQuestionView(question: Question) {
        val questionLabel = findViewById<TextView>(R.id.question_text)
        questionLabel.text = question.text

        val possibleAnswers = question.possibleAnswers

        val answerOne = findViewById<RadioButton>(R.id.answer_one)
        answerOne.text = possibleAnswers[0]

        val answerTwo = findViewById<RadioButton>(R.id.answer_two)
        answerTwo.text = possibleAnswers[1]

        val answerThree = findViewById<RadioButton>(R.id.answer_three)
        answerThree.text = possibleAnswers[2]

        val answerFour = findViewById<RadioButton>(R.id.answer_four)
        answerFour.text = possibleAnswers[3]

        val submitButton = findViewById<Button>(R.id.submit_button)
        submitButton.setOnClickListener {





        }
    }
}