package com.example.a1stapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(val postModel: List<PostModel>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private lateinit var mlistener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener) {
        mlistener = listener
    }

    class PostViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){
        private val postTitle: TextView = itemView.findViewById(R.id.postTitle)
        private val postBody: TextView = itemView.findViewById(R.id.postBody)

        fun bindView(postModel: PostModel) {
            postTitle.text = postModel.title
            postBody.text = postModel.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        return holder.bindView(postModel[position])
    }

    override fun getItemCount(): Int {
        return postModel.size
    }
}
