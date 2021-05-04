package br.com.mesa.newsapp.data.repository

import br.com.mesa.newsapp.data.model.Auth
import br.com.mesa.newsapp.data.model.LoginResponse
import br.com.mesa.newsapp.data.remote.LoginService
import br.com.mesa.newsapp.domain.LoginRepository

class LoginRepositoryImp(
    private val apiService: LoginService
) : LoginRepository {
    override suspend fun getAuth(auth: Auth): LoginResponse {
        return apiService.getAuth(auth)
    }
}