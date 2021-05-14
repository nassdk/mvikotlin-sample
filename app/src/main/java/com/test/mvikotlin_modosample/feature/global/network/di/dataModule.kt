package com.test.mvikotlin_modosample.feature.global.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.test.mvikotlin_modosample.BuildConfig
import com.test.mvikotlin_modosample.feature.global.network.ApiService
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val dataModule = module {

    single {

        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(15, TimeUnit.SECONDS)
        builder.connectTimeout(10, TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
        }

        val contentType = "application/json".toMediaType()
        val jsonConfig = JsonConfiguration.Stable.copy(
            isLenient = true,
            prettyPrint = true,
            ignoreUnknownKeys = true
        )
        val json = Json(jsonConfig)

        Retrofit
            .Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient.build())
            .build()
    }

    factory<ApiService> { get<Retrofit>().create(ApiService::class.java) }
}