package com.samueljuma.retrofitinaction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samueljuma.retrofitinaction.models.Comment
import com.samueljuma.retrofitinaction.network.NetworkResult
import com.samueljuma.retrofitinaction.repository.CommentsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CommentsUIState(
    val comments: List<Comment> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false
)

class CommentsViewModel(
    private val repository: CommentsRepository
): ViewModel() {

    private val _commentsUIState = MutableStateFlow(CommentsUIState())
    val commentsUIState: StateFlow<CommentsUIState> = _commentsUIState.asStateFlow()

    init {
        getComments()
    }

    private fun getComments(){

        _commentsUIState.value = _commentsUIState.value.copy(isLoading = true, comments = emptyList())

        viewModelScope.launch {
            val result = repository.getComments()
            when(result){

                is NetworkResult.Success -> {
                    _commentsUIState.update {
                        it.copy(
                            comments = result.data,
                            isLoading = false,
                        )
                    }
                }

                is NetworkResult.Error -> {
                    _commentsUIState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}