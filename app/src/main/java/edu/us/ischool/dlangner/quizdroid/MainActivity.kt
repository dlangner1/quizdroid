package edu.us.ischool.dlangner.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listOfQuestions = createListOfQuestionsForTopics()

        val listOfTopics = listOf(
            Topic("Math", "Did you pass the third grade?", listOfQuestions[0]),
            Topic("Physics", "Some of the most hard hitting, " +
                    "and not harding hitting, questions about PHYSICS", listOfQuestions[1]),
            Topic("Marvel Super Heroes", "Did you even watch the Avengers?", listOfQuestions[2])
        )

        val topicsAdapter = TopicRecyclerAdapter(listOfTopics)
        // TODO: On Click, display a new activity to represent the first question
        topicsAdapter.onTopicClickedListener = { topic ->
            val intent = Intent(this, OverviewActivity::class.java)

            intent.putExtra("TOPIC_DATA", topic)

            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = topicsAdapter
        recyclerView.setHasFixedSize(true)
    }

    private fun createListOfQuestionsForTopics(): List<List<Question>> {
        var topicQuestions: MutableList<List<Question>> = mutableListOf()

        val mathQuestions = listOf(
            Question("What is 2 + 2?", listOf("4", "27", "8", "Who knows?"), 0),
            Question("What is 200 / 4?", listOf("25", "400", "50", "15"), 2)
        )

        val physicsQuestions = listOf(
            Question("Which of these has both magnitude and direction?",
                listOf("A scalar", "A vector", "An arrow", "A pointy thingy?"), 1)
        )

        val marvelQuestions = listOf(
            Question("What is the name of that one big green dude?",
                listOf("Mark Ruffalo", "Big Green", "Edward Norton", "The Hulk"), 3)
        )

        topicQuestions.add(mathQuestions)
        topicQuestions.add(physicsQuestions)
        topicQuestions.add(marvelQuestions)

        return topicQuestions
    }
}
