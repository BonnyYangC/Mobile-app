package com.example.a1stapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var postAdapter: PostAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getPosts()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PostAdapter(listOf<PostModel>())

        call.enqueue(object: retrofit2.Callback<PostsData> {
            override fun onResponse(call: retrofit2.Call<PostsData>, response: retrofit2.Response<PostsData>) {
                if (response.isSuccessful) {
                    val sections = response.body()?.data?.sections;
                    //for ((key, value) in sections ) {
                    //    Log.e("success", value.toString())
                    //}
                    recyclerView.apply {
                        //adapter = PostAdapter(response.body()?.data?.sections.toString())
                        //layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<PostsData>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }

        })
    }


}