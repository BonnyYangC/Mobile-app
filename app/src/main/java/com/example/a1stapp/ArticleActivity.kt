package com.example.a1stapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val titleView = findViewById<TextView>(R.id.title_view)
        val bodyView = findViewById<TextView>(R.id.body_view)

        val bundle: Bundle? = intent.extras
        titleView.text = bundle!!.getString("title")
        bodyView.text = bundle.getString("body")
    }
}