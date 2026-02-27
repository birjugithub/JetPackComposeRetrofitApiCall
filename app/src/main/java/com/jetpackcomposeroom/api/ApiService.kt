package com.jetpackcomposeroom.api

import com.jetpackcomposeroom.model.PostData
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPost(): List<PostData>


}