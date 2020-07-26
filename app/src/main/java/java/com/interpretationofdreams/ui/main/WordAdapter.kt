package java.com.interpretationofdreams.ui.main

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import splitties.views.onClick
import java.com.interpretationofdreams.data.local.localentity.Words

class WordAdapter(private val words: List<Words>) : RecyclerView.Adapter<WordAdapter.WordHolder>() {

    var listener: ((Words) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WordHolder(WordItemUI(parent.context).root)

    override fun getItemCount() = words.size

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        holder.bindData(words[position])
        holder.itemView.onClick { listener?.invoke(words[position]) }
    }

    inner class WordHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var text: TextView = view.findViewById(WordItemUI.tvWord)

        fun bindData(words: Words) {
            text.text = words.categoryName
        }
    }
}