package br.com.mesa.newsapp.di

import br.com.mesa.newsapp.presentation.home.HomeViewModel
import br.com.mesa.newsapp.presentation.home.adapter.NewsAdapter
import br.com.mesa.newsapp.presentation.auth.AuthViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { AuthViewModel(get()) }
    factory { NewsAdapter() }
    single { createNewsRepository(get()) }
    single { createLoginRepository(get()) }
}