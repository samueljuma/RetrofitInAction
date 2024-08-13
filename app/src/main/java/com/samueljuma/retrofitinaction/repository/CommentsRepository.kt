package com.samueljuma.retrofitinaction.repository

import com.samueljuma.retrofitinaction.models.Comment
import com.samueljuma.retrofitinaction.network.CommentsAPIService
import com.samueljuma.retrofitinaction.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface CommentsRepository {
    suspend fun getComments(): NetworkResult<List<Comment>>
}

class CommentsRepositoryImpl(
    private val apiService: CommentsAPIService,
    private val dispatcher: CoroutineDispatcher
): CommentsRepository {

    override suspend fun getComments(): NetworkResult<List<Comment>> {
        return withContext(dispatcher){
            try{
                val response = apiService.getComments()
                if(response.isSuccessful){
                    val comments = response.body() ?: emptyList()
                    NetworkResult.Success(comments)
                }else{
                    NetworkResult.Error(response.message())
                }
            }catch (e: Exception){
                NetworkResult.Error(e.message ?: "Something went wrong")
            }
        }
    }
}