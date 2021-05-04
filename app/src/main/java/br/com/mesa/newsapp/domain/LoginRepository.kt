package br.com.mesa.newsapp.domain

import br.com.mesa.newsapp.data.model.Auth
import br.com.mesa.newsapp.data.model.LoginResponse

interface LoginRepository {
    suspend fun getAuth(auth: Auth): LoginResponse
}