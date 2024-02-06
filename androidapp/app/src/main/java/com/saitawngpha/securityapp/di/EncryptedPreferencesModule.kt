package com.saitawngpha.securityapp.di

import android.content.Context
import com.saitawngpha.securityapp.data.EncryptedPreferencesImpl
import com.saitawngpha.securityapp.domain.EncryptedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 24/01/2024.
 */

@Module
@InstallIn(SingletonComponent::class)
object EncryptedPreferencesModule {
    @Provides
    @Singleton
    fun provideEncryptedPreferences(
        @ApplicationContext context: Context
    ): EncryptedPreferences {
        return EncryptedPreferencesImpl(context = context)
    }
}