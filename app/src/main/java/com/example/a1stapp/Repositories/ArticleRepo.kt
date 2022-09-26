package com.example.a1stapp.Repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.a1stapp.Network.ApiService
import com.example.a1stapp.Common.Common
import com.example.a1stapp.Model.PostsData
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class ArticleRepo {
    private val apiService: ApiService

    companion object {
        private const val TAG = "ArticalRepo"
    }

    init {
        apiService = Common.getApiService(ApiService::class.java)
    }

    val getArticleLiveData: MutableLiveData<PostsData>
    get() {
        val data: MutableLiveData<PostsData> = MutableLiveData<PostsData>()

        apiService.getPosts().enqueue(object: Callback<PostsData> {
            override fun onResponse(call: Call<PostsData>, response: Response<PostsData>) {
                if (response.isSuccessful) {
                    //postAdapter.articles = response.body()?.data?.articles
                    //postAdapter.notifyDataSetChanged()
                    data.value = response.body()
                }
                else {
                    data.value = null
                }
            }

            override fun onFailure(call: Call<PostsData>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
                data.value = null
            }
        })
        return data
    }
}