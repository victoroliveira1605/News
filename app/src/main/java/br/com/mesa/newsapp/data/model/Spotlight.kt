package br.com.mesa.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class Spotlight(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("highlight")
    val highlight: Boolean,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)