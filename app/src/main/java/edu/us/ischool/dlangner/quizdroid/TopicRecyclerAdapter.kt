package edu.us.ischool.dlangner.quizdroid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import kotlinx.android.synthetic.main.list_item_view.view.*

class TopicRecyclerAdapter(private val listOfTopics: List<String>): RecyclerView.Adapter<TopicRecyclerAdapter.TopicViewHolder>() {

    var onTopicClickedListener: ((position: Int, name: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewHolderType: Int): TopicViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_view, parent, false)
        return TopicViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listOfTopics.size
    }

    override fun onBindViewHolder(viewHolder: TopicViewHolder, position: Int) {
        viewHolder.bindView(listOfTopics[position], position)
    }

    inner class TopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(topicName: String, position: Int) {
            itemView.topic_tv.text = topicName

            itemView.setOnClickListener {
                onTopicClickedListener?.invoke(position, topicName)
            }
        }
    }
}