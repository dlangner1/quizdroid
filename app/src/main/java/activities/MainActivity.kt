package activities

import adapters.TopicRecyclerAdapter
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import edu.us.ischool.dlangner.quizdroid.QuizApp
import edu.us.ischool.dlangner.quizdroid.R

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val USER_PREF_KEY = "USER_PREFERENCES_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(USER_PREF_KEY, Context.MODE_PRIVATE)
        // set initial file preferences
        sharedPreferences
            .edit()
            .putString(R.string.data_path.toString(), "questions.json")
            .apply()

        setSupportActionBar(findViewById(R.id.my_toolbar))

        val listOfTopics = QuizApp.sharedInstance.topicRepository.listOfTopics

        val topicsAdapter = TopicRecyclerAdapter(listOfTopics)
        topicsAdapter.onTopicClickedListener = { topic ->
            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            intent.putExtra("TOPIC_DATA", topic)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = topicsAdapter
        recyclerView.setHasFixedSize(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.settings -> {
            val intent = Intent(this@MainActivity, PreferencesActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
