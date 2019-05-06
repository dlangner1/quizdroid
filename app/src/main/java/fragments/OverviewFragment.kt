package fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import models.Question
import edu.us.ischool.dlangner.quizdroid.R
import models.Topic

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val TOPIC = "topic"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [OverviewFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [OverviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class OverviewFragment : Fragment() {
    private var topic: Topic? = null
    private var listener: OnFragmentInteractionListener? = null

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onBeginButtonPressed(questions: ArrayList<Question>)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            topic = it.getSerializable(TOPIC) as? Topic
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_overview, container, false)

        val topicTitleView = rootView.findViewById<TextView>(R.id.topic_title_label)
        topicTitleView.text = topic?.title

        val topicDescriptionView = rootView.findViewById<TextView>(R.id.topic_description_label)
        topicDescriptionView.text = topic?.description

        val numOfQuestions = rootView.findViewById<TextView>(R.id.topic_num_questions_label)

        if (topic?.questions!!.size > 1) {
            numOfQuestions.text = "".plus(topic?.questions!!.size).plus(" Questions")
        } else {
            numOfQuestions.text = "".plus(topic?.questions!!.size).plus(" Question")
        }

        val startQuizButton = rootView.findViewById<Button>(R.id.start_quiz_button)
        startQuizButton.setOnClickListener {
            onBeginQuizPressed(topic?.questions!!)
        }

        return rootView
    }

    private fun onBeginQuizPressed(questions: ArrayList<Question>) {
        listener?.onBeginButtonPressed(questions)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString().plus(" must implement OnFragmentInteractionListener"))
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        /**
         * @param topic The quiz topic selected.
         * @return A new instance of fragment OverviewFragment.
         */
        @JvmStatic
        fun newInstance(topic: Topic) =
            OverviewFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(TOPIC, topic)
                }
            }
    }
}
