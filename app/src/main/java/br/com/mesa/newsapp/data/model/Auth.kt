package br.com.mesa.newsapp.data.model


import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)