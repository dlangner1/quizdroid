package activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import edu.us.ischool.dlangner.quizdroid.QuizApp
import edu.us.ischool.dlangner.quizdroid.R
import fragments.AnswerFragment
import fragments.OverviewFragment
import fragments.QuestionFragment
import models.Answer
import models.Question

private const val OVERVIEW_FRAGMENT_TAG = "OVERVIEW_FRAGMENT_TAG"
private const val QUESTION_FRAGMENT_TAG = "QUESTION_FRAGMENT_TAG"
private const val ANSWER_FRAGMENT_TAG = "ANSWER_FRAGMENT_TAG"

class QuizActivity : AppCompatActivity(),
    OverviewFragment.OnFragmentInteractionListener,
    QuestionFragment.OnFragmentInteractionListener,
    AnswerFragment.OnFragmentInteractionListener {

    private lateinit var topicName: String
    var questionNum = 0
    var numCorrect = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        topicName = intent.extras.get("TOPIC") as String

        val topic = QuizApp.sharedInstance.topicRepository.getTopicWithName(topicName)

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
        val questions = QuizApp.sharedInstance.topicRepository.getQuestions(topicName)
        val currentQuestion = questions[questionNum]
        val correctAnswer = currentQuestion.possibleAnswers[currentQuestion.correctAnswer]

        // Update questionNum
        questionNum += 1

        // update Num correct if neccessary
        if (userAnswer == correctAnswer) {
            this.numCorrect += 1
        }

        // Create answer fragment and replace current fragment in container
        val answer = Answer(
            userAnswer,
            correctAnswer,
            this.numCorrect,
            this.questionNum,
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
            val questions = QuizApp.sharedInstance.topicRepository.getQuestions(this.topicName)

            val questionFragment =
                QuestionFragment.newInstance(questions[this.questionNum])
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
