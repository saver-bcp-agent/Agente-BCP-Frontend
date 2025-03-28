package com.isil.wardia

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://agente-production.up.railway.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ChatService = retrofit.create(ChatService::class.java)
}