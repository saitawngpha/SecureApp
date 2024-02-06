package com.saitawngpha.securityapp.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.saitawngpha.securityapp.domain.KeyProviderServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 24/01/2024.
 */

private const val BASE_URL = "http://10.0.2.2:8080"

@Module
@InstallIn(SingletonComponent::class)
object KeyProviderModule {

    @Provides
    @Singleton
    fun provideApiService(): KeyProviderServices {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
        return retrofit.create(KeyProviderServices::class.java)
    }
}