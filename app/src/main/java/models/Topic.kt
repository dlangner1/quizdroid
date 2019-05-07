package models

import java.io.Serializable

data class Topic(val title: String, val shortDescription: String,
                 val longDescription: String, val questions: ArrayList<Question>) : Serializable
