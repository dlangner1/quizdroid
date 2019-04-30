package edu.us.ischool.dlangner.quizdroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Button

class OverviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)

        val topic = intent.extras.get("TOPIC_DATA") as Topic

        val topicTitleView = findViewById<TextView>(R.id.topic_title_label)
        topicTitleView.text = topic.title

        val topicDescriptionView = findViewById<TextView>(R.id.topic_description_label)
        topicDescriptionView.text = topic.description

        val numOfQuestions = findViewById<TextView>(R.id.topic_num_questions_label)
        if (topic.questions.size > 1) {
            numOfQuestions.text = "".plus(topic.questions.size).plus(" Questions")
        } else {
            numOfQuestions.text = "".plus(topic.questions.size).plus(" Question")
        }

        val startQuizButton = findViewById<Button>(R.id.start_quiz_button)
        startQuizButton.setOnClickListener {
            val intent = Intent(this@OverviewActivity, QuestionActivity::class.java)
            intent.putExtra("QUESTION_DATA", topic.questions)
            intent.putExtra("QUESTION_NUM", 0)
            intent.putExtra("NUM_CORRECT", 0)
            startActivity(intent)
            finish()
        }
    }
}