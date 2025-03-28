package com.isil.wardia
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatService {
    @POST("chat")
    fun sendMessage(@Body request: ChatRequest): Call<ChatResponse>

    @GET("ahorros/{user_id}")
    fun getAhorros(@Path("user_id") userId: String): Call<List<AhorroItem>>

}