//package edu.us.ischool.dlangner.quizdroid
//
//import android.content.Intent
//import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
//import android.widget.Button
//import android.widget.RadioButton
//import android.widget.RadioGroup
//import android.widget.TextView
//
//class QuestionActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        // setContentView(R.layout.activity_question)
//
//        val questions = intent.extras.get("QUESTION_DATA") as ArrayList<Question>
//        val currentQuestionNum = intent.extras.get("QUESTION_NUM") as Int
//
//        val currentQuestion = questions[currentQuestionNum]
//
//        populateQuestionView(currentQuestion, currentQuestionNum, questions)
//    }
//
//    private fun populateQuestionView(question: Question, currentQuestionNum: Int, questions: ArrayList<Question>) {
//        val numCorrect = intent.extras.get("NUM_CORRECT") as Int
//        val possibleAnswers = question.possibleAnswers
//
//        val questionLabel = findViewById<TextView>(R.id.question_text)
//        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)
//        val submitButton = findViewById<Button>(R.id.submit_button)
//
//        val answerOne = findViewById<RadioButton>(R.id.answer_one)
//        val answerTwo = findViewById<RadioButton>(R.id.answer_two)
//        val answerThree = findViewById<RadioButton>(R.id.answer_three)
//        val answerFour = findViewById<RadioButton>(R.id.answer_four)
//
//        questionLabel.text = question.text
//        answerOne.text = possibleAnswers[0]
//        answerTwo.text = possibleAnswers[1]
//        answerThree.text = possibleAnswers[2]
//        answerFour.text = possibleAnswers[3]
//
//        submitButton.isEnabled = false
//        submitButton.setOnClickListener {
//            val checkedRadioButtonId = radioGroup.checkedRadioButtonId
//            val selectedRadioButton = radioGroup.findViewById<RadioButton>(checkedRadioButtonId)
//
//            val userAnswerText = selectedRadioButton.text
//            val correctAnswerText = question.possibleAnswers[question.correctAnswer]
//
//            var currNumCorrect = when(userAnswerText == correctAnswerText) {
//                true -> numCorrect + 1
//                false -> numCorrect
//            }
//
//            val answer = Answer(userAnswerText.toString(), correctAnswerText, currNumCorrect)
//            val intent = Intent(this@QuestionActivity, AnswerActivity::class.java)
//            intent.putExtra("ANSWER_DATA", answer)
//            intent.putExtra("QUESTION_DATA", questions)
//            intent.putExtra("QUESTION_NUM", currentQuestionNum)
//            startActivity(intent)
//            finish()
//        }
//
//        radioGroup.setOnCheckedChangeListener(
//            RadioGroup.OnCheckedChangeListener { _, _ ->
//                submitButton.isEnabled = true
//            }
//        )
//    }
//}