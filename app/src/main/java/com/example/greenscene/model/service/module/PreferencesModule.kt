package com.example.greenscene.model.service.module

import com.example.greenscene.model.AppPreferences
import com.example.greenscene.model.preference.AppPreferenceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {
    @Binds
    abstract fun provideAppPreferences(appPreferences: AppPreferenceImpl): AppPreferences
}
