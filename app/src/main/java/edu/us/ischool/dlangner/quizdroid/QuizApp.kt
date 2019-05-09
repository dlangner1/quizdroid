package edu.us.ischool.dlangner.quizdroid

import android.app.Application
import android.util.Log

private const val QUIZ_APP = "QUIZ_APP"

class QuizApp: Application() {
    companion object {
        lateinit var sharedInstance: QuizApp
            private set
    }

    lateinit var topicRepository: TopicRepository

    override fun onCreate() {
        super.onCreate()
        Log.i(QUIZ_APP, "Quiz App Application extension is connected!")

        sharedInstance = this
        topicRepository = TopicRepository(applicationContext)
    }
}
