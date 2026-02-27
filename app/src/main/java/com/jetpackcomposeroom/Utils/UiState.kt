package com.jetpackcomposeroom.Utils

import com.jetpackcomposeroom.model.PostData

sealed class UiState {
    object Loading: UiState()

    data class Success(private val post: List<PostData>): UiState()
    data class Failure(private val message: String): UiState()


}