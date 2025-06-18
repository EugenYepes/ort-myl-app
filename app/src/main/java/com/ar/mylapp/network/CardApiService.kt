package com.ar.mylapp.network

import ar.com.myldtos.cards.CardDTO
import com.ar.mylapp.models.cardProperties.CollectionDTO
import com.ar.mylapp.models.cardProperties.FormatDTO
import com.ar.mylapp.models.cardProperties.KeyWordDTO
import com.ar.mylapp.models.cardProperties.PlayerCardRequest
import com.ar.mylapp.models.cardProperties.PlayerCardProperties
import com.ar.mylapp.models.cardProperties.RaceDTO
import com.ar.mylapp.models.cardProperties.RarityDTO
import com.ar.mylapp.models.cardProperties.TypeDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CardApiService {
    @GET("cards")
    suspend fun getCards(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<List<CardDTO>>

    //Búsqueda de cartas por nombre en CardsScreen
    @GET("card/search")
    suspend fun searchCards(
        @Query("name") name: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): Response<List<CardDTO>>


    @GET("card/{id}")
    suspend fun getCardById(@Path("id") id: Int): Response<CardDTO>

    @GET("cards")
    suspend fun getFilteredCards(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("typeId") typeIds: List<Int>?,
        @Query("rarityId") rarityIds: List<Int>?,
        @Query("raceId") raceIds: List<Int>?,
        @Query("formatId") formatIds: List<Int>?,
        @Query("collectionId") collectionIds: List<Int>?,
        @Query("keyWordId") keywordIds: List<Int>?
    ): Response<List<CardDTO>>

    @GET("types") suspend fun getTypes(): List<TypeDTO>
    @GET("rarities") suspend fun getRarities(): List<RarityDTO>
    @GET("races") suspend fun getRaces(): List<RaceDTO>
    @GET("formats") suspend fun getFormats(): List<FormatDTO>
    @GET("collections") suspend fun getCollections(): List<CollectionDTO>
    @GET("keywords") suspend fun getKeywords(): List<KeyWordDTO>

    @GET("player/cards")
    suspend fun getPlayerCards(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<List<PlayerCardProperties>>

    @POST("player/cards")
    suspend fun addPlayerCards(
        @Header("Authorization") token: String,
        @Body cards: List<PlayerCardRequest>
    ): Response<Unit>

    @HTTP(method = "DELETE", path = "player/cards", hasBody = true)
    suspend fun deletePlayerCards(
        @Header("Authorization") token: String,
        @Body cards: List<PlayerCardRequest>
    ): Response<Unit>
}