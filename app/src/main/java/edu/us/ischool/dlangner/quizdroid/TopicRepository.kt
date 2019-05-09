package edu.us.ischool.dlangner.quizdroid

import android.content.Context
import models.Question
import models.Topic
import org.json.JSONArray
import org.json.JSONObject

class TopicRepository {

    var listOfTopics: List<Topic>

    companion object {
        const val JSON_FILE_PATH = "data/questions.json"

        const val TITLE = "title"
        const val DESCRIPTION = "desc"
        const val QUESTIONS = "questions"

        const val TEXT = "text"
        const val ANSWER = "answer"
        const val ANSWERS = "answers"
    }

    constructor(context: Context) {
        val jsonArray = parseJson(context)
        listOfTopics = createTopicModels(jsonArray)
    }

    private fun createTopicModels(jsonArray: JSONArray): List<Topic> {
        var topics = arrayListOf<Topic>()

        for (i in 0 until jsonArray.length()) {
            val quizObject = jsonArray.get(i) as JSONObject

            val topicTitle = quizObject.get(TITLE) as String
            val desc = quizObject.get(DESCRIPTION) as String
            val questionJsonObject = quizObject.get(QUESTIONS) as JSONArray

            val questions = createQuestionModel(questionJsonObject)

            val topic = Topic(topicTitle, "Take the quiz to find out", desc, questions)
            topics.add(topic)
        }
        return topics
    }

    private fun createQuestionModel(questions: JSONArray): ArrayList<Question> {
        val result = arrayListOf<Question>()

        for (i in 0 until questions.length()) {

            val question = questions.get(i) as JSONObject

            val questionText = question.get(TEXT) as String
            val correctAnswer = question.get(ANSWER) as String
            val correctAnswerInt = (correctAnswer.toInt()) - 1

            val jsonAnswers = question.get(ANSWERS) as JSONArray
            val possibleAnswers = createAnswers(jsonAnswers)

            result.add(Question(questionText, possibleAnswers, correctAnswerInt))
        }
        return result
    }

    private fun createAnswers(jsonAnswers: JSONArray): List<String> {
        var possibleAnswers = arrayListOf<String>()

        for (i in 0 until jsonAnswers.length()) {
            possibleAnswers.add(jsonAnswers.get(i) as String)
        }
        return possibleAnswers
    }

    private fun parseJson(context: Context): JSONArray {
        val jsonString: String? = try {
            // grab file from assets folder & read it to a String
            val inputStream = context.assets.open(JSON_FILE_PATH)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            String(buffer, Charsets.UTF_8)
        } catch (e: Exception) {
            null
        }

        return JSONArray(jsonString)
    }
}
