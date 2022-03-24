package com.example.imageapp.data.repository

import com.example.imageapp.data.network.NetworkService
import com.example.imageapp.data.toDomain
import com.example.imageapp.domain.model.ImageDomainModel
import com.example.imageapp.domain.repository.ImageRepository

class ImageRepositoryImpl(private val networkService: NetworkService): ImageRepository {
    override suspend fun getImage(): List<ImageDomainModel> {
        return networkService.getImages().map { it.toDomain() }
    }
}