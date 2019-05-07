package edu.us.ischool.dlangner.quizdroid

import models.Question
import models.Topic

const val MATH = "Math"
const val PHYSICS = "Physics"
const val MARVEL_SUPER_HEROES = "Marvel Super Heroes"

class TopicRepository {

    private val topicToQuestions: Map<String, ArrayList<Question>>
    private val mapOfTopics: Map<String, Topic>

    constructor() {
        topicToQuestions = createListOfQuestions()
        mapOfTopics = createTopics()
    }

    fun getQuestions(topic: String): ArrayList<Question> {
        return topicToQuestions.getValue(topic)
    }

    fun getTopicList(): List<Topic> {
        return ArrayList(mapOfTopics.values)
    }

    fun getTopicWithName(name: String): Topic {
        return mapOfTopics.getValue(name)
    }

    private fun createTopics(): Map<String, Topic> {
        return mapOf(
            MATH to
            Topic(MATH,
                "Did you pass the third grade?",
                "Here are some of the easiest math questions that you will come across, " +
                        "unless of course you are a child",
                getQuestions(MATH)
            ),
            PHYSICS to
            Topic(
                PHYSICS,
                "Test your physics knowledge!",
                "Some of the most hard hitting, " +
                        "and not harding hitting, questions about PHYSICS",
                getQuestions(PHYSICS)
            ),
            MARVEL_SUPER_HEROES to
            Topic(
                MARVEL_SUPER_HEROES,
                "Did you even watch the Avengers?",
                "Your knowledge of the Marvel universe will be tested at the greatest lengths. " +
                        "You have to be a REALLY big Marvel fan to answer these questions...maybe",
                getQuestions(MARVEL_SUPER_HEROES)
            )
        )
    }

    private fun createListOfQuestions(): Map<String, ArrayList<Question>> {
        val mathQuestions = arrayListOf(
            Question("What is 2 + 2?", listOf("4", "27", "8", "Who knows?"), 0),
            Question("What is 200 / 4?", listOf("25", "400", "50", "15"), 2)
        )

        val physicsQuestions = arrayListOf(
            Question(
                "Which of these has both magnitude and direction?",
                listOf("A scalar", "A vector", "An arrow", "A pointy thingy?"), 1
            )
        )

        val marvelQuestions = arrayListOf(
            Question(
                "What is the name of that one big green dude?",
                listOf("Mark Ruffalo", "Big Green Man", "Edward Norton", "The Hulk"), 3
            )
        )

        return mapOf(
            "Math" to mathQuestions,
            "Physics" to physicsQuestions,
            "Marvel Super Heroes" to marvelQuestions
        )
    }
}
