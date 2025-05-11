package com.ar.mylapp.network

import com.ar.mylapp.repository.GetServiceCardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstance {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        //Creo Retrofit
        return Retrofit.Builder()
            //Defino Url base
            .baseUrl(Config.baseUrl)
            //Defino canal de comunicación (Gson)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    //Se crea CardApiService y ya existe en todo el proyecto
    fun provideCardApiClient(retrofit: Retrofit): CardApiService {
        return retrofit.create(CardApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideCardRepository(cardRetrofit: CardRetrofit): GetServiceCardRepository {
        return GetServiceCardRepository(cardRetrofit)
    }

}