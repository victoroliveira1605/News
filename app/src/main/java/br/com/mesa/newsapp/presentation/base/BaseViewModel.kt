package br.com.mesa.newsapp.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.mesa.newsapp.data.model.ErrorModel
import br.com.mesa.newsapp.data.remote.ApiErrorHandle

abstract class BaseViewModel : ViewModel() {

    val scope = CoroutineScope(
        Job() + Dispatchers.Main
    )

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

    fun getError(e: Exception) : ErrorModel {
        return ApiErrorHandle.traceErrorException(e)
    }

    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

}