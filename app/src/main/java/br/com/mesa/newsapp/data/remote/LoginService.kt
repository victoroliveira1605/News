package br.com.mesa.newsapp.data.remote

import br.com.mesa.newsapp.data.model.Auth
import br.com.mesa.newsapp.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("v1/client/auth/signin")
    suspend fun getAuth(
        @Body auth: Auth
    ): LoginResponse
}