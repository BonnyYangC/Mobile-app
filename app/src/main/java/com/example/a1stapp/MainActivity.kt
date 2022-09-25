package com.example.a1stapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a1stapp.data.Article
import com.example.a1stapp.data.PostsData

class MainActivity : AppCompatActivity(), PostAdapter.OnItemClickListener {

    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getPosts()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        postAdapter = PostAdapter(this)
        recyclerView.adapter = postAdapter

        call.enqueue(object: retrofit2.Callback<PostsData> {
            override fun onResponse(call: retrofit2.Call<PostsData>, response: retrofit2.Response<PostsData>) {
                if (response.isSuccessful) {
                    postAdapter.articles = response.body()?.data?.articles
                    postAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: retrofit2.Call<PostsData>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }
        })
    }

    override fun onItemClick(article: Article?) {
        Toast.makeText(this@MainActivity, "test click", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@MainActivity, ArticleActivity::class.java)
        intent.putExtra("title", article?.title)
        intent.putExtra("body", article?.description)
        startActivity(intent)
    }
}