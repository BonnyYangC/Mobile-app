package com.example.a1stapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a1stapp.data.Article
import com.example.a1stapp.data.PostModel

class PostAdapter(private val listener: OnItemClickListener?, var articles: List<Article>? = null): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(article: Article?)
    }

    class PostViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){
        private val postTitle: TextView = itemView.findViewById(R.id.postTitle)
        private val postBody: TextView = itemView.findViewById(R.id.postBody)

        fun bindView(article: Article, listener: OnItemClickListener?) {
            postTitle.text = article.title
            postBody.text = article.description

            itemView.setOnClickListener {
                listener?.onItemClick(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        articles?.get(position)?.let {
            holder.bindView(it, listener)
        }
    }

    override fun getItemCount(): Int {
        return articles?.size ?: 0
    }
}
