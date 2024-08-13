package com.samueljuma.retrofitinaction.models

import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)