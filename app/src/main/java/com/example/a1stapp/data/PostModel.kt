package com.example.a1stapp.data

import com.example.a1stapp.ImageModel

data class PostModel(
    val description: String,
    val duration: Any,
    val imageModels: List<ImageModel>,
    val publishDate: String,
    val tag: String,
    val title: String,
    val type: String,
    val url: String,
    val uuid: String
)