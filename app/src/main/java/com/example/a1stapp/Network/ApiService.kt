package com.example.a1stapp.Network

import com.example.a1stapp.Model.PostsData
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("api/v1/sections/articles?sections=/news/collection/top-stories&limit=10&page=0")
    fun getPosts(): Call<PostsData>
}