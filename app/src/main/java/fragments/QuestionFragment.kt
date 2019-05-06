package fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import models.Question
import edu.us.ischool.dlangner.quizdroid.R

private const val QUESTION = "question"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [QuestionFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [QuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class QuestionFragment : Fragment() {
    private var question: Question? = null
    private var listener: OnFragmentInteractionListener? = null

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface OnFragmentInteractionListener {
        fun onSubmitAnswerButtonPressed(userAnswer: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            question = it.getSerializable(QUESTION) as Question
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_question, container, false)

        val questionLabel = rootView.findViewById<TextView>(R.id.question_text)
        val radioGroup = rootView.findViewById<RadioGroup>(R.id.radio_group)
        val submitButton = rootView.findViewById<Button>(R.id.submit_button)

        val answerOne = rootView.findViewById<RadioButton>(R.id.answer_one)
        val answerTwo = rootView.findViewById<RadioButton>(R.id.answer_two)
        val answerThree = rootView.findViewById<RadioButton>(R.id.answer_three)
        val answerFour = rootView.findViewById<RadioButton>(R.id.answer_four)

        questionLabel.text = question?.text

        radioGroup.setOnCheckedChangeListener { _, _ ->
            submitButton.isEnabled = true
        }

        val possibleAnswers = question!!.possibleAnswers
        answerOne.text = possibleAnswers[0]
        answerTwo.text = possibleAnswers[1]
        answerThree.text = possibleAnswers[2]
        answerFour.text = possibleAnswers[3]

        submitButton.isEnabled = false
        submitButton.setOnClickListener {
            val checkedRadioButtonId = radioGroup.checkedRadioButtonId
            val selectedRadioButton = radioGroup.findViewById<RadioButton>(checkedRadioButtonId)

            val userAnswerText = selectedRadioButton.text.toString()

            onSubmitButtonPressed(userAnswerText)
        }

        return rootView
    }

    private fun onSubmitButtonPressed(userAnswer: String) {
        listener?.onSubmitAnswerButtonPressed(userAnswer)
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

    companion object {
        /**
         * @param question The given question for the quiz.
         * @return A new instance of fragment QuestionFragment.
         */
        @JvmStatic
        fun newInstance(question: Question) =
            QuestionFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(QUESTION, question)
                }
            }
    }
}
