package com.example.a1stapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a1stapp.Adapter.PostAdapter
import com.example.a1stapp.R
import com.example.a1stapp.ViewModel.ArticleListActivityViewModel
import com.example.a1stapp.Model.Article

class ArticleListActivity : AppCompatActivity(), PostAdapter.OnItemClickListener {

    private var articleListActivityViewModel: ArticleListActivityViewModel? = null
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        postAdapter = PostAdapter(this)
        recyclerView.adapter = postAdapter

        articleListActivityViewModel = ViewModelProvider(this)[ArticleListActivityViewModel::class.java]
        articleListActivityViewModel!!.getArticleList.observe(this){ postsData ->
            postAdapter.articles = postsData?.data?.articles
            postAdapter.notifyDataSetChanged()
        }
    }

    override fun onItemClick(article: Article?) {
        val intent = Intent(this@ArticleListActivity, ArticleActivity::class.java)
        intent.putExtra("title", article?.title)
        intent.putExtra("body", article?.description)
        intent.putExtra("image", article?.imageUrl)
        startActivity(intent)
    }
}