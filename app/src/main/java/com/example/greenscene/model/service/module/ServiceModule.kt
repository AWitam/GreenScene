package com.example.greenscene.model.service.module

import com.example.greenscene.model.service.AccountService
import com.example.greenscene.model.service.RestaurantService
import com.example.greenscene.model.service.impl.AccountServiceImpl
import com.example.greenscene.model.service.impl.RestaurantServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideRestaurantService(impl: RestaurantServiceImpl): RestaurantService

}
