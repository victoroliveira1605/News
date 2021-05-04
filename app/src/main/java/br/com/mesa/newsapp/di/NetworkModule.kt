package br.com.mesa.newsapp.di

import android.content.Context
import br.com.mesa.newsapp.BuildConfig
import br.com.mesa.newsapp.data.remote.LoginService
import br.com.mesa.newsapp.data.remote.NewsService
import br.com.mesa.newsapp.data.repository.LoginRepositoryImp
import br.com.mesa.newsapp.data.repository.NewsRepositoryImp
import br.com.mesa.newsapp.domain.LoginRepository
import br.com.mesa.newsapp.domain.NewsRepository
import br.com.mesa.newsapp.util.Preferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val NetworkModule = module {
    single { createServiceNews(get()) }
    single { createServiceLogin(get()) }
    single { createRetrofit(get(), BuildConfig.BASE_URL) }
    single { createOkHttpClient(get()) }
}

fun createOkHttpClient(context: Context): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor { chain -> createParametersDefault(chain, context) }
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun createParametersDefault(chain: Interceptor.Chain, context: Context): Response {
    var request = chain.request()
    val builder = request.url.newBuilder()
    Preferences(context).getValueString(Preferences.USER_TOKEN).let {
        request = if (it != null) {
            request.newBuilder()
                .addHeader(
                    "Authorization", it
                )
                .url(builder.build()).build()
        } else {
            request.newBuilder()
                .url(builder.build()).build()
        }
    }


    return chain.proceed(request)
}

fun createServiceNews(retrofit: Retrofit): NewsService {
    return retrofit.create(NewsService::class.java)
}

fun createServiceLogin(retrofit: Retrofit): LoginService {
    return retrofit.create(LoginService::class.java)
}

fun createNewsRepository(apiService: NewsService): NewsRepository {
    return NewsRepositoryImp(apiService)
}

fun createLoginRepository(apiService: LoginService): LoginRepository {
    return LoginRepositoryImp(apiService)
}