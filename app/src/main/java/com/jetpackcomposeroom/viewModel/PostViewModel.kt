package com.jetpackcomposeroom.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackcomposeroom.model.PostData
import com.jetpackcomposeroom.repo.PostRepository
import kotlinx.coroutines.launch
class PostViewModel : ViewModel() {
    private val repository = PostRepository()
    private val _creditCards = MutableLiveData<List<PostData>>()
    val creditCards: LiveData<List<PostData>> = _creditCards

    init {
        fetchCreditCards()
    }

    fun fetchCreditCards() {
        viewModelScope.launch {
            try {
                val cards = repository.getPost()
                _creditCards.value = cards
            } catch (e: Exception) {
                // Handle error
            }
        }

    }
}