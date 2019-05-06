//package edu.us.ischool.dlangner.quizdroid
//
//import android.content.Intent
//import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
//import android.widget.Button
//import android.widget.TextView
//
//class AnswerActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_answer)
//
//        val answer = intent.extras.get("ANSWER_DATA") as Answer
//        val questions = intent.extras.get("QUESTION_DATA") as ArrayList<Question>
//        val currentQuestionNum = intent.extras.get("QUESTION_NUM") as Int
//
//        val correctAnswerLabel = findViewById<TextView>(R.id.correct_answer_label)
//        val userAnswerLabel = findViewById<TextView>(R.id.user_answer_label)
//        val numCorrectLabel = findViewById<TextView>(R.id.num_correct_label)
//
//
//        val correctAnswerText = "The correct answer is: \n" + answer.correctAnswer
//        correctAnswerLabel.text = correctAnswerText
//
//        val userAnswerText = "You answered: \n" + answer.given
//        userAnswerLabel.text = userAnswerText
//
//        val numCorrectString = "You have " + answer.numCorrect.toString() +
//                " out of " + questions.size + " correct"
//        numCorrectLabel.text = numCorrectString
//
//        setupAnswerButton(currentQuestionNum, questions, answer.numCorrect)
//    }
//
//    private fun setupAnswerButton(currentQuestionNum: Int,
//                                  questions: ArrayList<Question>, numCorrect: Int) {
//        val answerButton = findViewById<Button>(R.id.answer_button)
//
//        val nextQuestionNum = currentQuestionNum + 1
//
//        if (nextQuestionNum >= questions.size) {
//            answerButton.text = "Finish"
//            answerButton.setOnClickListener {
//                finish()
//            }
//        } else {
//            answerButton.text = "Next"
//            answerButton.setOnClickListener {
//                // Do everything that is being done on overview
//                val intent = Intent(this@AnswerActivity, QuestionActivity::class.java)
//                intent.putExtra("QUESTION_DATA", questions)
//                intent.putExtra("QUESTION_NUM", nextQuestionNum)
//                intent.putExtra("NUM_CORRECT", numCorrect)
//                startActivity(intent)
//                finish()
//            }
//        }
//    }
//}