package fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Button
import android.view.ViewGroup
import models.Answer
import edu.us.ischool.dlangner.quizdroid.R

private const val ANSWER = "ANSWER"

class AnswerFragment : Fragment() {
    private var answer: Answer? = null
    private var listener: OnFragmentInteractionListener? = null

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface OnFragmentInteractionListener {
        fun onNextOrFinishButtonPressed(isQuizFinished: Boolean)
    }

    companion object {
        /**
         * @param answer Answer data about the question at hand.
         * @return A new instance of fragment AnswerFragment.
         */
        @JvmStatic
        fun newInstance(answer: Answer) =
            AnswerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ANSWER, answer)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            answer = it.getSerializable(ANSWER) as? Answer
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_answer, container, false)

        val correctAnswerLabel = rootView.findViewById<TextView>(R.id.correct_answer_label)
        val userAnswerLabel = rootView.findViewById<TextView>(R.id.user_answer_label)
        val numCorrectLabel = rootView.findViewById<TextView>(R.id.num_correct_label)
        val answerButton = rootView.findViewById<Button>(R.id.answer_button)

        val correctAnswerText = "The correct answer is: \n" + answer?.correctAnswer
        correctAnswerLabel.text = correctAnswerText

        val userAnswerText = "You answered: \n" + answer?.given
        userAnswerLabel.text = userAnswerText

        val numCorrectString = "You have " + answer?.numCorrect.toString() +
                " out of " + answer?.totalQuestionNum.toString() + " correct"
        numCorrectLabel.text = numCorrectString

        val isQuizFinished = answer!!.nextQuestionNum >= answer!!.totalQuestionNum

        answerButton.text = if (isQuizFinished) "Finish" else "Next"
        answerButton.setOnClickListener {
            onNextOrFinishButtonPressed(isQuizFinished)
        }

        return rootView
    }

    private fun onNextOrFinishButtonPressed(isQuizFinished: Boolean) {
        listener?.onNextOrFinishButtonPressed(isQuizFinished)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
