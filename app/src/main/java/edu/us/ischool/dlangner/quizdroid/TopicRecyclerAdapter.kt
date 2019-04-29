package edu.us.ischool.dlangner.quizdroid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import kotlinx.android.synthetic.main.list_item_view.view.*

class TopicRecyclerAdapter(private val listOfTopics: List<Topic>): RecyclerView.Adapter<TopicRecyclerAdapter.TopicViewHolder>() {

    var onTopicClickedListener: ((topic: Topic) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewHolderType: Int): TopicViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_view, parent, false)
        return TopicViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listOfTopics.size
    }

    override fun onBindViewHolder(viewHolder: TopicViewHolder, position: Int) {
        viewHolder.bindView(listOfTopics[position])
    }

    inner class TopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(topic: Topic) {
            itemView.topic_tv.text = topic.title

            itemView.setOnClickListener {
                onTopicClickedListener?.invoke(topic)
            }
        }
    }
}