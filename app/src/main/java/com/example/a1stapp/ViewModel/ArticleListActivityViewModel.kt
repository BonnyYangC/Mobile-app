package com.example.a1stapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.a1stapp.Repositories.ArticleRepo
import com.example.a1stapp.Model.PostsData

class ArticleListActivityViewModel: ViewModel() {

    private val articleRepo: ArticleRepo
    val getArticleList: LiveData<PostsData>
    get() = articleRepo.getArticleLiveData

    init {
        articleRepo = ArticleRepo()
    }
}