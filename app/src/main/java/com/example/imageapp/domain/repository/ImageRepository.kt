package com.example.imageapp.domain.repository

import com.example.imageapp.domain.model.ImageDomainModel

interface ImageRepository {

    suspend fun getImage(): List<ImageDomainModel>
}