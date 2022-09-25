package com.example.a1stapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a1stapp.data.Article
import com.squareup.picasso.Picasso

class PostAdapter(private val listener: OnItemClickListener?, var articles: List<Article>? = null): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(article: Article?)
    }

    class PostViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){
        private val titleView: TextView = itemView.findViewById(R.id.row_article_title)
        private val imageView: ImageView = itemView.findViewById(R.id.row_article_image)

        fun bindView(article: Article, listener: OnItemClickListener?) {
            titleView.text = article.title

            Picasso.get().load(article.imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imageView)

            itemView.setOnClickListener {
                listener?.onItemClick(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_article, parent, false)
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
