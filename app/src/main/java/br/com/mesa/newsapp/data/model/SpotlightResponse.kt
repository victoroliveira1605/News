package br.com.mesa.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class SpotlightResponse(
    @SerializedName("data")
    val data: List<Spotlight>,
    @SerializedName("pagination")
    val pagination: Pagination
)