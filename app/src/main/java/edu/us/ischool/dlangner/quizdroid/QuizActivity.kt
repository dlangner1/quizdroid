package edu.us.ischool.dlangner.quizdroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

private const val OVERVIEW_FRAGMENT_TAG = "OVERVIEW_FRAGMENT_TAG"
private const val QUESTION_FRAGMENT_TAG = "QUESTION_FRAGMENT_TAG"
private const val ANSWER_FRAGMENT_TAG = "ANSWER_FRAGMENT_TAG"

class QuizActivity : AppCompatActivity(), OverviewFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val topic = intent.extras.get("TOPIC_DATA") as Topic

        val overviewFragment = OverviewFragment.newInstance(topic)

        supportFragmentManager.beginTransaction().run {
            add(R.id.quiz_container, overviewFragment, OVERVIEW_FRAGMENT_TAG)
            commit()
        }
    }

    override fun onFragmentInteraction(questions: ArrayList<Question>) {
        val something = questions
    }
}
