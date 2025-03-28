package com.isil.wardia
import com.google.gson.annotations.SerializedName

data class ChatResponse(
    @SerializedName("response")
    val response: String,
)
