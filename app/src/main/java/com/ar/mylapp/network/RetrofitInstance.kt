package com.ar.mylapp.network

import com.ar.mylapp.repository.DeckRepository
import ar.com.myldtos.users.UserDTO
import com.ar.mylapp.repository.GetServiceCardRepository
import com.ar.mylapp.repository.StoreRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstance {

    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        val gson = GsonBuilder()
            .registerTypeAdapter(UserDTO::class.java, UserDtoDeserializer())
            .create()

        return Retrofit.Builder()
            .baseUrl(Config.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
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

    @Singleton
    @Provides
    fun provideAuthApiClient(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideStoreApiClient(retrofit: Retrofit): StoreApiService {
        return retrofit.create(StoreApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideStoreRetrofit(service: StoreApiService): StoreRetrofit {
        return StoreRetrofit(service)
    }

    @Provides
    @Singleton
    fun provideStoreRepository(storeRetrofit: StoreRetrofit): StoreRepository {
        return StoreRepository(storeRetrofit)
    }

    @Provides
    @Singleton
    fun provideDeckApiClient(retrofit: Retrofit): DeckApiService {
        return retrofit.create(DeckApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDeckRepository(deckRetrofit: DeckRetrofit): DeckRepository {
        return DeckRepository(deckRetrofit)
    }

    @Provides
    @Singleton
    fun provideDeckRetrofit(service: DeckApiService): DeckRetrofit {
        return DeckRetrofit(service)
    }


}