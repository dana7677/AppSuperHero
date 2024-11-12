package com.example.superhero.utils
import com.example.superhero.retrofit.superHeroService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    companion object
    {
        fun makeRetrofitService():superHeroService
        {
            val retrofit= Retrofit.Builder()
                .baseUrl("https://superheroapi.com/api/d185787618b6c92dbb52a73ac316744e/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(superHeroService::class.java)
        }
    }
}