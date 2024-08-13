package com.samueljuma.retrofitinaction.network

import com.samueljuma.retrofitinaction.models.Comment
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET

private val json = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
}
private val contentType = "application/json".toMediaType()


object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun create(): Retrofit =
        Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
}

interface CommentsAPIService {
    @GET("comments")
    suspend fun getComments(): Response<List<Comment>>
}