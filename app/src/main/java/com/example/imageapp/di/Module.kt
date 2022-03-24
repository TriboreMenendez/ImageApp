package com.example.imageapp.di

import com.example.imageapp.data.network.NetworkService
import com.example.imageapp.data.repository.ImageRepositoryImpl
import com.example.imageapp.domain.repository.ImageRepository
import com.example.imageapp.domain.usecase.GetImageUseCase
import com.example.imageapp.utils.Const
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    @Singleton
    fun provideGetMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideGetRetrofit(moshi: Moshi): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideGetNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideGetRepositoryImpl(networkService: NetworkService): ImageRepositoryImpl {
        return ImageRepositoryImpl(networkService)
    }

    @Provides
    @Singleton
    fun provideGetImageUseCase(repo: ImageRepositoryImpl): GetImageUseCase {
        return GetImageUseCase(repo)
    }
}