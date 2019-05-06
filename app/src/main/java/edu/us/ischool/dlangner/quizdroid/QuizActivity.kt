package edu.us.ischool.dlangner.quizdroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

private const val OVERVIEW_FRAGMENT_TAG = "OVERVIEW_FRAGMENT_TAG"
private const val QUESTION_FRAGMENT_TAG = "QUESTION_FRAGMENT_TAG"
private const val ANSWER_FRAGMENT_TAG = "ANSWER_FRAGMENT_TAG"

private const val QUESTIONS = "QUESTIONS"
private const val QUESTION_NUM = "QUESTION_NUM"
private const val NUM_CORRECT = "NUM_CORRECT"

class QuizActivity : AppCompatActivity(),
    OverviewFragment.OnFragmentInteractionListener,
    QuestionFragment.OnFragmentInteractionListener {

    private var savedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        this.savedInstanceState = savedInstanceState

        val topic = intent.extras.get("TOPIC_DATA") as Topic

        val overviewFragment = OverviewFragment.newInstance(topic)
        supportFragmentManager.beginTransaction().run {
            add(R.id.quiz_container, overviewFragment, OVERVIEW_FRAGMENT_TAG)
            commit()
        }
    }

    override fun onBeginButtonPressed(questions: ArrayList<Question>) {
        savedInstanceState?.putSerializable(QUESTIONS, questions)
        savedInstanceState?.putInt(QUESTION_NUM, 0)
        savedInstanceState?.putInt(NUM_CORRECT, 0)

        val questionFragment = QuestionFragment.newInstance(questions[0])
        supportFragmentManager.beginTransaction().run {
            replace(R.id.quiz_container, questionFragment, QUESTION_FRAGMENT_TAG)
            commit()
        }
    }

    override fun onSubmitAnswerPressed(userAnswer: String) {
        // update Bundle variables as neccesary
        // then show off Answer Fragment
    }
}
