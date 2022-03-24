package com.example.imageapp.data.network

import retrofit2.http.GET

// https://dev-tasks.alef.im/task-m-001/list.php

interface NetworkService {
    @GET("list.php")
    suspend fun getImages(): List<String>
}