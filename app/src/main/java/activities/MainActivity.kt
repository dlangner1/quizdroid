package activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import edu.us.ischool.dlangner.quizdroid.R
import adapters.TopicRecyclerAdapter
import edu.us.ischool.dlangner.quizdroid.QuizApp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listOfTopics = QuizApp.sharedInstance.topicRepository.getTopicList()

        val topicsAdapter = TopicRecyclerAdapter(listOfTopics)
        topicsAdapter.onTopicClickedListener = { topic ->
            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            intent.putExtra("TOPIC", topic.title)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = topicsAdapter
        recyclerView.setHasFixedSize(true)
    }
}
