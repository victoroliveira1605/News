package br.com.mesa.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("data")
    val data: List<News>,
    @SerializedName("pagination")
    val pagination: Pagination
)