package br.com.mesa.newsapp.presentation.auth

import androidx.lifecycle.MutableLiveData
import br.com.mesa.newsapp.data.model.Auth
import br.com.mesa.newsapp.domain.LoginRepository
import br.com.mesa.newsapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(private val loginRepository: LoginRepository) : BaseViewModel() {
    val token: MutableLiveData<String> = MutableLiveData();
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getAuth(auth: Auth) {
        isLoading.value = true
        scope.launch {
            try {
                val response = loginRepository.getAuth(auth)
                token.value = response.token
            } catch (e: Exception) {
                errorMessage.value = getError(e).getErrorMessage()
            }
            isLoading.value = false
        }
    }

}