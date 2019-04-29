package edu.us.ischool.dlangner.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listOfTopics = listOf("Math", "Physics", "Marvel Super Heroes")

        val topicsAdapter = TopicRecyclerAdapter(listOfTopics)
        // TODO: On Click, display a new activity to represent the first question
        topicsAdapter.onTopicClickedListener = { position, name ->  
            println()
        }

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = topicsAdapter
        recyclerView.setHasFixedSize(true)
    }
}
