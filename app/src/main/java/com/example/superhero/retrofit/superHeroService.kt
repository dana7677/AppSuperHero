package com.example.superhero.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface superHeroService {

    @GET("search/{name}")
    suspend fun getHeroeName(
        @Path("name") query:String
    ):SuperHeroeResponse
    @GET("{character-id}")
    suspend fun getHeroeID(
        @Path("character-id") id:String
    ):DataHero

}
