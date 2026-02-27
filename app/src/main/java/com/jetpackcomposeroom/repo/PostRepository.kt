package com.jetpackcomposeroom.repo

import com.jetpackcomposeroom.api.ApiService
import com.jetpackcomposeroom.api.RetrofitInstance
import com.jetpackcomposeroom.model.PostData

class PostRepository {

    private val apiService = RetrofitInstance.apiService
    suspend fun getPost(): List<PostData> {
        return apiService.getPost()
    }



}