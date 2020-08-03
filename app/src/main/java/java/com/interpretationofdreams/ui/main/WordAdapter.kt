package java.com.interpretationofdreams.ui.main

import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import splitties.views.onClick
import java.com.interpretationofdreams.R
import java.com.interpretationofdreams.data.local.localentity.Words

class WordAdapter() :
    PagedListAdapter<Words, WordAdapter.TopicHolder>(TaskDiffCallback()) {
    var listener: ((Words) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TopicHolder(WordItemUI(parent.context).root)

    override fun onBindViewHolder(holder: TopicHolder, position: Int) {
        try {
            holder.bind(getItem(position)!!)
        } catch (e: Exception) {

        }
    }

    inner class TopicHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(topic: Words) {
            itemView.onClick { listener?.invoke(topic) }
            itemView.findViewById<TextView>(WordItemUI.tvWord).text = topic.categoryName
            val animation = AnimationUtils.loadAnimation(itemView.context, R.anim.slide_in_right)
            itemView.animation = animation
            itemView.animate()
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<Words>() {
        override fun areItemsTheSame(oldItem: Words, newItem: Words) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Words, newItem: Words) = oldItem == newItem
    }
}