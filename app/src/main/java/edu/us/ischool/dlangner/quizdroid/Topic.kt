package edu.us.ischool.dlangner.quizdroid

import java.io.Serializable

data class Topic(val title: String, val description: String, val questions: ArrayList<Question>) : Serializable
