package edu.us.ischool.dlangner.quizdroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fragments.AnswerFragment
import fragments.OverviewFragment
import fragments.QuestionFragment
import models.Answer
import models.Question
import models.Topic

private const val OVERVIEW_FRAGMENT_TAG = "OVERVIEW_FRAGMENT_TAG"
private const val QUESTION_FRAGMENT_TAG = "QUESTION_FRAGMENT_TAG"
private const val ANSWER_FRAGMENT_TAG = "ANSWER_FRAGMENT_TAG"

private const val QUESTIONS = "QUESTIONS"
private const val QUESTION_NUM = "QUESTION_NUM"
private const val NUM_CORRECT = "NUM_CORRECT"

class QuizActivity : AppCompatActivity(),
    OverviewFragment.OnFragmentInteractionListener,
    QuestionFragment.OnFragmentInteractionListener,
    AnswerFragment.OnFragmentInteractionListener {

    private var savedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        this.savedInstanceState = Bundle()

        val topic = intent.extras.get("TOPIC_DATA") as Topic

        val overviewFragment = OverviewFragment.newInstance(topic)
        supportFragmentManager.beginTransaction().run {
            add(
                R.id.quiz_container, overviewFragment,
                OVERVIEW_FRAGMENT_TAG
            )
            commit()
        }
    }

    //region Fragment Listener callbacks
    override fun onBeginButtonPressed(questions: ArrayList<Question>) {
        savedInstanceState?.putSerializable(QUESTIONS, questions)
        savedInstanceState?.putInt(QUESTION_NUM, 0)
        savedInstanceState?.putInt(NUM_CORRECT, 0)

        val questionFragment = QuestionFragment.newInstance(questions[0])
        supportFragmentManager.beginTransaction().run {
            replace(
                R.id.quiz_container, questionFragment,
                QUESTION_FRAGMENT_TAG
            )
            commit()
        }
    }

    override fun onSubmitAnswerButtonPressed(userAnswer: String) {
        val questions =savedInstanceState?.getSerializable(QUESTIONS) as ArrayList<Question>
        val questionNum = savedInstanceState?.getInt(QUESTION_NUM) as Int
        val currentNumCorrect = savedInstanceState?.getInt(NUM_CORRECT) as Int

        val currentQuestion = questions[questionNum]
        val correctAnswer = currentQuestion.possibleAnswers[currentQuestion.correctAnswer]

        // Update questionNum
        val nextQuestionNum = questionNum + 1
        savedInstanceState?.putInt(QUESTION_NUM, nextQuestionNum)

        // update Num correct
        val newNumCorrect = if (userAnswer == correctAnswer) currentNumCorrect + 1 else currentNumCorrect
        savedInstanceState?.putInt(NUM_CORRECT, newNumCorrect)

        // Create answer fragment and replace current fragment in container
        val answer = Answer(
            userAnswer,
            correctAnswer,
            newNumCorrect,
            nextQuestionNum,
            questions.size
        )
        val answerFragment = AnswerFragment.newInstance(answer)
        supportFragmentManager.beginTransaction().run {
            replace(
                R.id.quiz_container, answerFragment,
                ANSWER_FRAGMENT_TAG
            )
            commit()
        }
    }

    override fun onNextOrFinishButtonPressed(isQuizFinished: Boolean) {
        // if the quiz is finished go back to quiz selection screen
        if (isQuizFinished) {
            finish()
        } else { // Otherwise, show next question
            val questions = savedInstanceState?.getSerializable(QUESTIONS) as ArrayList<Question>
            val questionNum = savedInstanceState?.getInt(QUESTION_NUM) as Int

            val questionFragment =
                QuestionFragment.newInstance(questions[questionNum])
            supportFragmentManager.beginTransaction().run {
                replace(
                    R.id.quiz_container, questionFragment,
                    QUESTION_FRAGMENT_TAG
                )
                commit()
            }
        }
    }
    //endregion
}
