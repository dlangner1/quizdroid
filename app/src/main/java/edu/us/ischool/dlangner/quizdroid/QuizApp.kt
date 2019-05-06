package edu.us.ischool.dlangner.quizdroid

import android.app.Application
import android.util.Log

private const val QUIZ_APP = "QUIZ_APP"


class QuizApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Log.i(QUIZ_APP, "Application override is connected!")


    }
}